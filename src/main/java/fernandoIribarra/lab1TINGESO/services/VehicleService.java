package fernandoIribarra.lab1TINGESO.services;

import fernandoIribarra.lab1TINGESO.entities.VehicleEntity;
import fernandoIribarra.lab1TINGESO.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {
    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    @Lazy
    RepairService repairService;

    public VehicleEntity saveVehicle(VehicleEntity vehicle) { return vehicleRepository.save(vehicle);}

    public VehicleEntity getVehicleById(Long id) { return vehicleRepository.findById(id).get(); }

    public List<Long> getFormulaValues(long id) {
        VehicleEntity vehicle = getVehicleById(id);
        List<Long> values = new ArrayList<>();
        values.add(repairService.repairNumberDiscount(vehicle, 100));
        values.add(repairService.bonusDiscount(vehicle,false));
        values.add(repairService.antiquityRecharge(vehicle, 100));
        values.add(repairService.mileageRecharge(vehicle, 100));
        return values;
    }

    public List<VehicleEntity> getAllVehicles() { return vehicleRepository.findAll(); }

    public VehicleEntity updateVehicle(VehicleEntity vehicle) {return vehicleRepository.save(vehicle);}

    public boolean deleteVehicle(Long id) throws Exception {
        try {
            vehicleRepository.deleteById(id);
            repairService.deleteRepairsWithVehicle(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
