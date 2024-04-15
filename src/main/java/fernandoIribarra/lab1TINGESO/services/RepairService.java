package fernandoIribarra.lab1TINGESO.services;

import fernandoIribarra.lab1TINGESO.entities.RepairEntity;
import fernandoIribarra.lab1TINGESO.entities.VehicleEntity;
import fernandoIribarra.lab1TINGESO.repositories.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RepairService {
    @Autowired
    RepairRepository repairRepository;

    @Autowired
    VehicleService vehicleService;

    public RepairEntity saveRepair(RepairEntity repair) { return repairRepository.save(repair);}

    public RepairEntity getRepairById(Long id) { return repairRepository.findById(id).get();}

    public List<RepairEntity> getAllRepairs() { return repairRepository.findAll(); }

    public RepairEntity updateRepair(RepairEntity repair) { return repairRepository.save(repair);}

    public RepairEntity calculateTotalCost(RepairEntity repair) {
        long totalCost = 0L;
        Map<String, List<Long>> baseRepairCosts = new HashMap<>();
        baseRepairCosts.put("Gasolina",
                List.of(120_000L,130_000L,350_000L,210_000L,
                        150_000L,100_000L,100_000L,180_000L,
                        150_000L,130_000L,80_000L));
        baseRepairCosts.put("Diesel",
                List.of(120_000L,130_000L,450_000L,210_000L,
                        150_000L,120_000L,100_000L,180_000L,
                        150_000L,140_000L,80_000L));
        baseRepairCosts.put("Hibrido",
                List.of(180_000L,190_000L,700_000L,300_000L,
                        200_000L,450_000L,100_000L,210_000L,
                        180_000L,220_000L,80_000L));
        baseRepairCosts.put("Electrico",
                List.of(220_000L,230_000L,800_000L,300_000L,
                        250_000L,0L,100_000L,250_000L,
                        180_000L,0L,80_000L));

        VehicleEntity vehicle = vehicleService.getVehicleById(repair.getId_vehicle());

        long baseCost = baseRepairCosts.get(vehicle.getMotorType()).get(repair.getType());

        totalCost += baseCost;
        totalCost += mileageRecharge(vehicle, baseCost) + antiquityRecharge() + delayRecharge();
        totalCost -= repairNumberDiscount() + attentionDayDiscount() + bonusDiscount();

        repair.setTotalAmount(totalCost);
        return repair;
    }

    public Long mileageRecharge(VehicleEntity vehicle, long baseCost) { return 0L;}

    public Long antiquityRecharge() { return 0L;}

    public Long delayRecharge() { return 0L;}

    public Long repairNumberDiscount() { return 0L;}

    public Long attentionDayDiscount() { return 0L;}

    public Long bonusDiscount() { return 0L;}

    public boolean deleteRepair(Long id) throws Exception {
        try {
            repairRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
