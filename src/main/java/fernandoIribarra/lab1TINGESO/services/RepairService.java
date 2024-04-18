package fernandoIribarra.lab1TINGESO.services;

import fernandoIribarra.lab1TINGESO.entities.BonusEntity;
import fernandoIribarra.lab1TINGESO.entities.RepairEntity;
import fernandoIribarra.lab1TINGESO.entities.VehicleEntity;
import fernandoIribarra.lab1TINGESO.repositories.RepairRepository;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RepairService {
    @Autowired
    RepairRepository repairRepository;
    @Autowired
    VehicleService vehicleService;
    @Autowired
    BonusService bonusService;

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
        totalCost += mileageRecharge(vehicle, baseCost) + antiquityRecharge(vehicle, baseCost) + delayRecharge(repair, baseCost);
        totalCost -= repairNumberDiscount(vehicle, baseCost) + attentionDayDiscount(repair, baseCost) + bonusDiscount(vehicle);
        totalCost += Math.round(totalCost * Double.parseDouble(System.getenv("IVA")));

        repair.setTotalAmount(totalCost);
        return repair;
    }

    public Long mileageRecharge(VehicleEntity vehicle, long baseCost) {
        Map<String, List<Double>> mileageCosts = new HashMap<>();
        mileageCosts.put("Sedan",       List.of(0d, 0.03d, 0.07d, 0.12d, 0.2d));
        mileageCosts.put("Hatchback",   List.of(0d, 0.03d, 0.07d, 0.12d, 0.2d));
        mileageCosts.put("SUV",         List.of(0d, 0.05d, 0.09d, 0.12d, 0.2d));
        mileageCosts.put("Pickup",      List.of(0d, 0.05d, 0.09d, 0.12d, 0.2d));
        mileageCosts.put("Furgoneta",   List.of(0d, 0.05d, 0.09d, 0.12d, 0.2d));

        long mileageCost = 0L;

        long mileage = vehicle.getMileage();
        String vehicleType = vehicle.getType();

        if (0L <= mileage && mileage <= 5_000L) {
            mileageCost = Math.round(mileageCosts.get(vehicleType).get(0) * baseCost);
        } else if (5_001L <= mileage && mileage <= 12_000L) {
            mileageCost = Math.round(mileageCosts.get(vehicleType).get(1) * baseCost);
        } else if (12_001L <= mileage && mileage <= 25_000L) {
            mileageCost = Math.round(mileageCosts.get(vehicleType).get(2) * baseCost);
        } else if (25_001L <= mileage && mileage <= 40_000L) {
            mileageCost = Math.round(mileageCosts.get(vehicleType).get(3) * baseCost);
        } else if (40_001L <= mileage) {
            mileageCost = Math.round(mileageCosts.get(vehicleType).get(4) * baseCost);
        }

        return mileageCost;
    }

    public Long antiquityRecharge(VehicleEntity vehicle, long baseCost) {
        Map<String, List<Double>> antiquityCosts = new HashMap<>();
        antiquityCosts.put("Sedan",       List.of(0d, 0.05d, 0.09d, 0.15d));
        antiquityCosts.put("Hatchback",   List.of(0d, 0.05d, 0.09d, 0.15d));
        antiquityCosts.put("SUV",         List.of(0d, 0.07d, 0.11d, 0.2d));
        antiquityCosts.put("Pickup",      List.of(0d, 0.07d, 0.11d, 0.2d));
        antiquityCosts.put("Furgoneta",   List.of(0d, 0.07d, 0.11d, 0.2d));

        long antiquityCost = 0L;

        String vehicleType = vehicle.getType();
        long antiquity = Math.abs(ChronoUnit.YEARS.between(LocalDate.now(), vehicle.getFabricationDate()));

        if (0L <= antiquity && antiquity <= 5L) {
            antiquityCost = Math.round(antiquityCosts.get(vehicleType).get(0) * baseCost);
        } else if (6L <= antiquity && antiquity <= 10L) {
            antiquityCost = Math.round(antiquityCosts.get(vehicleType).get(1) * baseCost);
        } else if (11L <= antiquity && antiquity <= 15L) {
            antiquityCost = Math.round(antiquityCosts.get(vehicleType).get(2) * baseCost);
        } else if (16L <= antiquity) {
            antiquityCost = Math.round(antiquityCosts.get(vehicleType).get(3) * baseCost);
        }

        return antiquityCost;
    }

    public Long delayRecharge(RepairEntity repair, long baseCost) {
        double rechargePerDay = 0.05d;
        long delay = Math.abs(ChronoUnit.DAYS.between(repair.getDateOfPickUp(), repair.getDateOfRelease()));
        return Math.round(delay * rechargePerDay * baseCost);
    }

    public Long repairNumberDiscount(VehicleEntity vehicle, long baseCost) {
        Map<String, List<Double>> antiquityCosts = new HashMap<>();
        antiquityCosts.put("Gasolina",      List.of(0.05d, 0.1d, 0.15d, 0.2d));
        antiquityCosts.put("Diesel",        List.of(0.07d, 0.12d, 0.17d, 0.22d));
        antiquityCosts.put("Hibrido",       List.of(0.1d, 0.15d, 0.2d, 0.25d));
        antiquityCosts.put("Electrico",     List.of(0.08d, 0.13d, 0.18d, 0.23d));

        int recentRepairNumber = 0;
        List<RepairEntity> vehicleRepairs = repairRepository.findVehicleRepairs(vehicle.getId());
        for (RepairEntity repair: vehicleRepairs) {
            long monthsBetween = Math.abs(ChronoUnit.MONTHS.between(repair.getDateOfPickUp(), LocalDateTime.now()));
            if (monthsBetween <= 12) {
                recentRepairNumber += 1;
            }
        }

        long repairNumberCost = 0;
        String motorType = vehicle.getMotorType();

        if (0L <= recentRepairNumber && recentRepairNumber <= 2L) {
            repairNumberCost = Math.round(antiquityCosts.get(motorType).get(0) * baseCost);
        } else if (3L <= recentRepairNumber && recentRepairNumber <= 5L) {
            repairNumberCost = Math.round(antiquityCosts.get(motorType).get(1) * baseCost);
        } else if (6L <= recentRepairNumber && recentRepairNumber <= 9L) {
            repairNumberCost = Math.round(antiquityCosts.get(motorType).get(2) * baseCost);
        } else if (10L <= recentRepairNumber) {
            repairNumberCost = Math.round(antiquityCosts.get(motorType).get(3) * baseCost);
        }

        return repairNumberCost;
    }

    public Long attentionDayDiscount(RepairEntity repair, long baseCost) {
        double attentionDayPonderator = 0.1;
        long attentionDayCost = 0;
        LocalDateTime admissionDate = repair.getDateOfAdmission();
        if (admissionDate.getDayOfWeek().toString().equals("MONDAY") ||
                admissionDate.getDayOfWeek().toString().equals("THURSDAY")){
            if (9 <= admissionDate.getHour() && admissionDate.getHour() < 12) {
                attentionDayCost = Math.round(baseCost * attentionDayPonderator);
            }
        }
        return attentionDayCost;
    }

    public Long bonusDiscount(VehicleEntity vehicle) {
        String brand = vehicle.getBrand();
        return bonusService.consumeBonus(brand);
    }

    public boolean deleteRepair(Long id) throws Exception {
        try {
            repairRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
