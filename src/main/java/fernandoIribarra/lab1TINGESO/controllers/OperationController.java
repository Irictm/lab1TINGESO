package fernandoIribarra.lab1TINGESO.controllers;

import fernandoIribarra.lab1TINGESO.entities.OperationEntity;
import fernandoIribarra.lab1TINGESO.services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/operation")
@CrossOrigin("*")
public class OperationController {
    @Autowired
    OperationService operationService;

    @GetMapping("/{id}")
    public ResponseEntity<OperationEntity> getOperationById(@PathVariable Long id) {
        OperationEntity operation = operationService.getOperationById(id);
        return ResponseEntity.ok(operation);
    }

    @PostMapping("/")
    public ResponseEntity<OperationEntity> saveOperation(@RequestBody OperationEntity operation) {
        OperationEntity newOperation = operationService.saveOperation(operation);
        return ResponseEntity.ok(newOperation);
    }

    @PutMapping("/")
    public ResponseEntity<OperationEntity> updateOperation(@RequestBody OperationEntity operation) {
        OperationEntity updatedOperation = operationService.updateOperation(operation);
        return ResponseEntity.ok(updatedOperation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteOperation(@PathVariable Long id) throws Exception {
        var isDeleted = operationService.deleteOperation(id);
        return ResponseEntity.noContent().build();
    }
}
