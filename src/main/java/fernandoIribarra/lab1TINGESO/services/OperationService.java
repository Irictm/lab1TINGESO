package fernandoIribarra.lab1TINGESO.services;

import fernandoIribarra.lab1TINGESO.entities.OperationEntity;
import fernandoIribarra.lab1TINGESO.repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OperationService {
    @Autowired
    OperationRepository operationRepository;

    public OperationEntity saveOperation(OperationEntity operation) { return operationRepository.save(operation);}

    public List<OperationEntity> getOperationsByRepair(Long id_repair) { return operationRepository.findOperationsByRepair(id_repair); }

    public OperationEntity getOperationById(Long id) { return operationRepository.findById(id).get(); }

    public OperationEntity updateOperation(OperationEntity operation) { return operationRepository.save(operation); }

    public long calculateBaseCost(OperationEntity operation, String typeOfMotor) {
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
        return baseRepairCosts.get(typeOfMotor).get(operation.getType()-1);
    }

    public long calculateTotalRepairBaseCost(Long id_repair, String typeOfMotor) {
        long totalBaseCost = 0L;
        List<OperationEntity> operations = getOperationsByRepair(id_repair);
        for (OperationEntity operation: operations) {
            totalBaseCost += calculateBaseCost(operation, typeOfMotor);
        }
        return totalBaseCost;
    }

    public boolean deleteOperationsWithRepair(Long id_repair) throws Exception {
        try {
            operationRepository.deleteOperationsByRepair(id_repair);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public boolean deleteOperation(Long id) throws Exception {
        try {
            operationRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
