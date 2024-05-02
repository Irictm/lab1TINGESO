package fernandoIribarra.lab1TINGESO.services;

import fernandoIribarra.lab1TINGESO.entities.RepairEntity;
import fernandoIribarra.lab1TINGESO.entities.VehicleEntity;
import fernandoIribarra.lab1TINGESO.repositories.RepairRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RepairServiceTest {

    @Mock
    private RepairRepository repairRepository;

    @Mock
    private OperationService operationService;

    @Mock
    private VehicleService vehicleService;

    @Mock
    private BonusService bonusService;

    @InjectMocks
    RepairService repairService = new RepairService();
    RepairEntity repair = new RepairEntity(1L,
            LocalDateTime.now(),
            5L,
            LocalDateTime.now(),
            LocalDateTime.now(),
            1L);

    @Test
    public void whenGetRepairById_thenRepair() {
        // Given
        // repair entity defined earlier
        when(repairRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(repair));

        // When
        RepairEntity foundRepair = repairService.getRepairById(repair.getId());

        // Then
        assertThat(foundRepair).isEqualTo(repair);
    }

    @Test
    public void whenGetAllRepairs_thenRepairs() {
        // Given
        // repair entity defined earlier
        RepairEntity repair1 = new RepairEntity(1L,
                LocalDateTime.now(),
                5L,
                LocalDateTime.now(),
                LocalDateTime.now(),
                1L);
        List<RepairEntity> repairs = List.of(repair, repair1);
        when(repairRepository.findAll()).thenReturn(repairs);

        // When
        List<RepairEntity> foundRepairs = repairService.getAllRepairs();

        // Then
        assertThat(foundRepairs).isEqualTo(repairs);
    }

    @Test
    public void whenGetAllRepairsWithOperationType_thenRepairs() {
        // Given
        // repair entity defined earlier
        RepairEntity repair1 = new RepairEntity(1L,
                LocalDateTime.now(),
                5L,
                LocalDateTime.now(),
                LocalDateTime.now(),
                1L);
        List<RepairEntity> repairs = List.of(repair, repair1);
        when(repairRepository.findRepairsWithOperation(Mockito.anyInt())).thenReturn(repairs);

        // When
        List<RepairEntity> foundRepairs = repairService.getAllRepairsWithOperationType(1);

        // Then
        assertThat(foundRepairs).isEqualTo(repairs);
    }

    @Test
    public void whenCalculateTotalCost_thenRepairWithTotalCost() {
        // Given
        // repair entity defined earlier
        VehicleEntity vehicle = new VehicleEntity(1L,
                "09-32-LA",
                "Toyota",
                "model1",
                "SUV",
                LocalDate.now(),
                "Diesel",
                4,
                4432L);
        when(repairRepository.save(Mockito.any(RepairEntity.class))).thenReturn(repair);
        when(repairRepository.findVehicleRepairs(Mockito.anyLong())).thenReturn(List.of(repair));
        when(vehicleService.getVehicleById(Mockito.anyLong())).thenReturn(vehicle);
        when(operationService.calculateTotalRepairBaseCost(Mockito.anyLong(), Mockito.anyString())).thenReturn(100_000L);
        when(bonusService.consumeBonus(Mockito.anyString(), Mockito.anyBoolean())).thenReturn(10_000L);

        // When
        RepairEntity foundRepair = repairService.calculateTotalCost(repair);

        // Then
        assertThat(foundRepair.getTotalAmount()).isEqualTo(98_770L);
    }

    @Test
    public void whenCalculateTotalCost_thenRepairWithTotalCostConsumingBonus() {
        // Given
        // repair entity defined earlier
        VehicleEntity vehicle = new VehicleEntity(1L,
                "09-32-LA",
                "Toyota",
                "model1",
                "SUV",
                LocalDate.now(),
                "Diesel",
                4,
                4432L);
        when(repairRepository.save(Mockito.any(RepairEntity.class))).thenReturn(repair);
        when(repairRepository.findVehicleRepairs(Mockito.anyLong())).thenReturn(List.of(repair));
        when(vehicleService.getVehicleById(Mockito.anyLong())).thenReturn(vehicle);
        when(operationService.calculateTotalRepairBaseCost(Mockito.anyLong(), Mockito.anyString())).thenReturn(100_000L);
        when(bonusService.consumeBonus(Mockito.anyString(), Mockito.anyBoolean())).thenReturn(10_000L);
        repair.setTotalAmount(0L);

        // When
        RepairEntity foundRepair = repairService.calculateTotalCost(repair);

        // Then
        assertThat(foundRepair.getTotalAmount()).isEqualTo(98_770L);
    }

    @Test
    public void whenCalculateTotalCost_thenRepairWithTotalCost0L() {
        // Given
        // repair entity defined earlier
        VehicleEntity vehicle = new VehicleEntity(1L,
                "09-32-LA",
                "Toyota",
                "model1",
                "SUV",
                LocalDate.now(),
                "Diesel",
                4,
                4432L);
        when(repairRepository.save(Mockito.any(RepairEntity.class))).thenReturn(repair);
        when(repairRepository.findVehicleRepairs(Mockito.anyLong())).thenReturn(List.of(repair));
        when(vehicleService.getVehicleById(Mockito.anyLong())).thenReturn(vehicle);
        when(operationService.calculateTotalRepairBaseCost(Mockito.anyLong(), Mockito.anyString())).thenReturn(-100_000_000L);
        when(bonusService.consumeBonus(Mockito.anyString(), Mockito.anyBoolean())).thenReturn(10_000L);

        // When
        RepairEntity foundRepair = repairService.calculateTotalCost(repair);

        // Then
        assertThat(foundRepair.getTotalAmount()).isEqualTo(0L);
    }

    @Test
    public void whenCalculateTotalCost_thenRepairWithNoChanges() {
        // Given
        // repair entity defined earlier
        when(vehicleService.getVehicleById(Mockito.anyLong())).thenReturn(null);
        repair.setTotalAmount(6L);

        // When
        RepairEntity foundRepair = repairService.calculateTotalCost(repair);

        // Then
        assertThat(foundRepair).isEqualTo(repair);
    }

    @Test
    public void whenMileageRecharge_thenRechargeValue0L() {
        //Given
        // repair entity defined earlier
        VehicleEntity vehicle = new VehicleEntity(1L,
                "09-32-LA",
                "Toyota",
                "model1",
                "Sedan",
                LocalDate.now(),
                "Diesel",
                4,
                0L);

        // When
        Long mileageRechargeValue = repairService.mileageRecharge(vehicle, 100L);

        // Then
        assertThat(mileageRechargeValue).isEqualTo(0L);
    }

    @Test
    public void whenMileageRecharge_thenRechargeValue3L() {
        //Given
        // repair entity defined earlier
        VehicleEntity vehicle = new VehicleEntity(1L,
                "09-32-LA",
                "Toyota",
                "model1",
                "Sedan",
                LocalDate.now(),
                "Diesel",
                4,
                6_000L);

        // When
        Long mileageRechargeValue = repairService.mileageRecharge(vehicle, 100L);

        // Then
        assertThat(mileageRechargeValue).isEqualTo(3L);
    }

    @Test
    public void whenMileageRecharge_thenRechargeValue7L() {
        //Given
        // repair entity defined earlier
        VehicleEntity vehicle = new VehicleEntity(1L,
                "09-32-LA",
                "Toyota",
                "model1",
                "Sedan",
                LocalDate.now(),
                "Diesel",
                4,
                13_000L);

        // When
        Long mileageRechargeValue = repairService.mileageRecharge(vehicle, 100L);

        // Then
        assertThat(mileageRechargeValue).isEqualTo(7L);
    }

    @Test
    public void whenMileageRecharge_thenRechargeValue12L() {
        //Given
        // repair entity defined earlier
        VehicleEntity vehicle = new VehicleEntity(1L,
                "09-32-LA",
                "Toyota",
                "model1",
                "Sedan",
                LocalDate.now(),
                "Diesel",
                4,
                26_000L);

        // When
        Long mileageRechargeValue = repairService.mileageRecharge(vehicle, 100L);

        // Then
        assertThat(mileageRechargeValue).isEqualTo(12L);
    }

    @Test
    public void whenMileageRecharge_thenRechargeValue20L() {
        //Given
        // repair entity defined earlier
        VehicleEntity vehicle = new VehicleEntity(1L,
                "09-32-LA",
                "Toyota",
                "model1",
                "Sedan",
                LocalDate.now(),
                "Diesel",
                4,
                41_000L);

        // When
        Long mileageRechargeValue = repairService.mileageRecharge(vehicle, 100L);

        // Then
        assertThat(mileageRechargeValue).isEqualTo(20L);
    }

    @Test
    public void whenAntiquityRecharge_thenRechargeValue0L() {
        //Given
        // repair entity defined earlier
        VehicleEntity vehicle = new VehicleEntity(1L,
                "09-32-LA",
                "Toyota",
                "model1",
                "Sedan",
                LocalDate.now(),
                "Diesel",
                4,
                41_000L);

        // When
        Long antiquityRechargeValue = repairService.antiquityRecharge(vehicle, 100L);

        // Then
        assertThat(antiquityRechargeValue).isEqualTo(0L);
    }

    @Test
    public void whenAntiquityRecharge_thenRechargeValue5L() {
        //Given
        // repair entity defined earlier
        VehicleEntity vehicle = new VehicleEntity(1L,
                "09-32-LA",
                "Toyota",
                "model1",
                "Sedan",
                LocalDate.now().minusYears(7),
                "Diesel",
                4,
                41_000L);

        // When
        Long antiquityRechargeValue = repairService.antiquityRecharge(vehicle, 100L);

        // Then
        assertThat(antiquityRechargeValue).isEqualTo(5L);
    }

    @Test
    public void whenAntiquityRecharge_thenRechargeValue9L() {
        //Given
        // repair entity defined earlier
        VehicleEntity vehicle = new VehicleEntity(1L,
                "09-32-LA",
                "Toyota",
                "model1",
                "Sedan",
                LocalDate.now().minusYears(12),
                "Diesel",
                4,
                41_000L);

        // When
        Long antiquityRechargeValue = repairService.antiquityRecharge(vehicle, 100L);

        // Then
        assertThat(antiquityRechargeValue).isEqualTo(9L);
    }

    @Test
    public void whenAntiquityRecharge_thenRechargeValue15L() {
        //Given
        // repair entity defined earlier
        VehicleEntity vehicle = new VehicleEntity(1L,
                "09-32-LA",
                "Toyota",
                "model1",
                "Sedan",
                LocalDate.now().minusYears(20),
                "Diesel",
                4,
                41_000L);

        // When
        Long antiquityRechargeValue = repairService.antiquityRecharge(vehicle, 100L);

        // Then
        assertThat(antiquityRechargeValue).isEqualTo(15L);
    }

    @Test
    public void whenDelayRecharge_thenRechargeValue15L() {
        //Given
        // repair entity defined earlier
        repair.setDateOfRelease(LocalDateTime.now().minusDays(3));
        repair.setDateOfPickUp(LocalDateTime.now());

        // When
        Long delayRechargeValue = repairService.delayRecharge(repair, 100L);

        // Then
        assertThat(delayRechargeValue).isEqualTo(15L);
    }

    @Test
    public void whenRepairNumberDiscount_thenDiscountValue7L() {
        //Given
        // repair entity defined earlier
        List<RepairEntity> repairs = new ArrayList<>(15);
        VehicleEntity vehicle = new VehicleEntity(1L,
                "09-32-LA",
                "Toyota",
                "model1",
                "Sedan",
                LocalDate.now(),
                "Diesel",
                4,
                41_000L);
        for (int i = 0; i < 2; i++) {
            RepairEntity repairNew = new RepairEntity(1L,
                    LocalDateTime.now(),
                    5L,
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    1L);
            repairs.add(repairNew);
        }
        when(repairRepository.findVehicleRepairs(Mockito.anyLong())).thenReturn(repairs);

        // When
        Long repairNumberDiscountValue = repairService.repairNumberDiscount(vehicle, 100L);

        // Then
        assertThat(repairNumberDiscountValue).isEqualTo(7L);
    }

    @Test
    public void whenRepairNumberDiscount_thenDiscountValue12L() {
        //Given
        // repair entity defined earlier
        List<RepairEntity> repairs = new ArrayList<>(15);
        VehicleEntity vehicle = new VehicleEntity(1L,
                "09-32-LA",
                "Toyota",
                "model1",
                "Sedan",
                LocalDate.now(),
                "Diesel",
                4,
                41_000L);
        for (int i = 0; i < 4; i++) {
            RepairEntity repairNew = new RepairEntity(1L,
                    LocalDateTime.now(),
                    5L,
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    1L);
            repairs.add(repairNew);
        }
        when(repairRepository.findVehicleRepairs(Mockito.anyLong())).thenReturn(repairs);

        // When
        Long repairNumberDiscountValue = repairService.repairNumberDiscount(vehicle, 100L);

        // Then
        assertThat(repairNumberDiscountValue).isEqualTo(12L);
    }

    @Test
    public void whenRepairNumberDiscount_thenDiscountValue17L() {
        //Given
        // repair entity defined earlier
        List<RepairEntity> repairs = new ArrayList<>(15);
        VehicleEntity vehicle = new VehicleEntity(1L,
                "09-32-LA",
                "Toyota",
                "model1",
                "Sedan",
                LocalDate.now(),
                "Diesel",
                4,
                41_000L);
        for (int i = 0; i < 7; i++) {
            RepairEntity repairNew = new RepairEntity(1L,
                    LocalDateTime.now(),
                    5L,
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    1L);
            repairs.add(repairNew);
        }
        when(repairRepository.findVehicleRepairs(Mockito.anyLong())).thenReturn(repairs);

        // When
        Long repairNumberDiscountValue = repairService.repairNumberDiscount(vehicle, 100L);

        // Then
        assertThat(repairNumberDiscountValue).isEqualTo(17L);
    }

    @Test
    public void whenRepairNumberDiscount_thenDiscountValue22L() {
        //Given
        // repair entity defined earlier
        List<RepairEntity> repairs = new ArrayList<>(15);
        VehicleEntity vehicle = new VehicleEntity(1L,
                "09-32-LA",
                "Toyota",
                "model1",
                "Sedan",
                LocalDate.now(),
                "Diesel",
                4,
                41_000L);
        for (int i = 0; i < 12; i++) {
            RepairEntity repairNew = new RepairEntity(1L,
                    LocalDateTime.now(),
                    5L,
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    1L);
            repairs.add(repairNew);
        }
        when(repairRepository.findVehicleRepairs(Mockito.anyLong())).thenReturn(repairs);

        // When
        Long repairNumberDiscountValue = repairService.repairNumberDiscount(vehicle, 100L);

        // Then
        assertThat(repairNumberDiscountValue).isEqualTo(22L);
    }

    @Test
    public void whenAttentionDayDiscount_thenDiscountValue10L() {
        //Given
        // repair entity defined earlier
        repair.setDateOfAdmission(LocalDateTime.of(2024, 4, 29, 11, 0));

        // When
        Long attentionDayDiscountValue = repairService.attentionDayDiscount(repair, 100L);

        // Then
        assertThat(attentionDayDiscountValue).isEqualTo(10L);
    }

    @Test
    public void whenBonusDiscount_thenDiscountValue10_000L() {
        //Given
        // repair entity defined earlier
        VehicleEntity vehicle = new VehicleEntity(1L,
                "09-32-LA",
                "Toyota",
                "model1",
                "Sedan",
                LocalDate.now(),
                "Diesel",
                4,
                41_000L);
        when(bonusService.consumeBonus(Mockito.anyString(), Mockito.anyBoolean())).thenReturn(10_000L);

        // When
        Long bonusDiscountValue = repairService.bonusDiscount(vehicle, false);

        // Then
        assertThat(bonusDiscountValue).isEqualTo(10_000L);
    }

    @Test
    public void whenSaveRepair_thenRepair() {
        // Given
        // repair entity defined earlier
        when(repairRepository.save(Mockito.any(RepairEntity.class))).thenReturn(repair);

        // When
        RepairEntity savedRepair = repairService.saveRepair(repair);

        // Then
        assertThat(savedRepair).isEqualTo(repair);
    }

    @Test
    public void whenUpdateRepair_thenRepair() {
        // Given
        // repair entity defined earlier
        when(repairRepository.save(Mockito.any(RepairEntity.class))).thenReturn(repair);

        // When
        RepairEntity updatedRepair = repairService.updateRepair(repair);

        // Then
        assertThat(updatedRepair).isEqualTo(repair);
    }

    @Test
    public void whenDeleteRepairsWithVehicle_thenTrue() throws Exception {
        // Given
        // repair entity defined earlier
        VehicleEntity vehicle = new VehicleEntity(1L,
                "09-32-LA",
                "Toyota",
                "model1",
                "Sedan",
                LocalDate.now(),
                "Diesel",
                4,
                41_000L);

        // When
        boolean response = repairService.deleteRepairsWithVehicle(vehicle.getId());

        // Then
        assertThat(response).isEqualTo(true);
    }

    @Test
    public void whenGetAvgRepairTimeOfBrand_thenAvgRepairTime() {
        // Given
        // repair entity defined earlier
        RepairEntity repair1 = new RepairEntity(1L,
                LocalDateTime.now().minusSeconds(10L),
                5L,
                LocalDateTime.now(),
                LocalDateTime.now(),
                1L);
        List<RepairEntity> repairs = List.of(repair, repair1);
        when(repairRepository.findRepairsWithBrand(Mockito.anyString())).thenReturn(repairs);

        // When
        Long avgtime = repairService.getAvgRepairTimeOfBrand("Toyota");

        // Then
        assertThat(avgtime).isEqualTo(5L);
    }

    @Test
    public void whenDeleteRepair_thenTrue() throws Exception {
        // Given
        // repair entity defined earlier

        // When
        boolean response = repairService.deleteRepair(repair.getId());

        // Then
        assertThat(response).isEqualTo(true);
    }
}
