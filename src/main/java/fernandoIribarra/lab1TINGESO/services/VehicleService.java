package fernandoIribarra.lab1TINGESO.services;

import fernandoIribarra.lab1TINGESO.entities.VehicleEntity;
import fernandoIribarra.lab1TINGESO.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
    @Autowired
    VehicleRepository vehicleRepository;

    public VehicleEntity saveVehicle(VehicleEntity vehicle) { return vehicleRepository.save(vehicle);}

    public VehicleEntity getVehicleById(Long id) { return vehicleRepository.findById(id).get(); }

    public VehicleEntity updateVehicle(VehicleEntity vehicle) {return vehicleRepository.save(vehicle);}

    public boolean deleteVehicle(Long id) throws Exception {
        try {
            vehicleRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
