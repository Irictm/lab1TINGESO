package fernandoIribarra.lab1TINGESO.controllers;

import fernandoIribarra.lab1TINGESO.entities.ReceiptEntity;
import fernandoIribarra.lab1TINGESO.services.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/receipt")
@CrossOrigin("*")
public class ReceiptController {
    @Autowired
    ReceiptService receiptService;

    @GetMapping("/{id}")
    public ResponseEntity<ReceiptEntity> getReceiptById(@PathVariable Long id) {
        ReceiptEntity receipt = receiptService.getReceiptById(id);
        return ResponseEntity.ok(receipt);
    }

    @PostMapping("/")
    public ResponseEntity<ReceiptEntity> saveReceipt(@RequestBody ReceiptEntity receipt) {
        ReceiptEntity newReceipt = receiptService.saveReceipt(receipt);
        return ResponseEntity.ok(newReceipt);
    }

    @PutMapping("/")
    public ResponseEntity<ReceiptEntity> updateReceipt(@RequestBody ReceiptEntity receipt) {
        ReceiptEntity updatedReceipt = receiptService.updateReceipt(receipt);
        return ResponseEntity.ok(updatedReceipt);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteReceipt(@PathVariable Long id) throws Exception {
        var isDeleted = receiptService.deleteReceipt(id);
        return ResponseEntity.noContent().build();
    }
}
