package fernandoIribarra.lab1TINGESO.services;

import fernandoIribarra.lab1TINGESO.entities.RepairEntity;
import fernandoIribarra.lab1TINGESO.repositories.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepairService {
    @Autowired
    RepairRepository repairRepository;

    public RepairEntity saveRepair(RepairEntity repair) { return repairRepository.save(repair);}

    public RepairEntity getRepairById(Long id) { return repairRepository.findById(id).get();}

    public RepairEntity updateRepair(RepairEntity repair) { return repairRepository.save(repair);}

    public boolean deleteRepair(Long id) throws Exception {
        try {
            repairRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
