package fernandoIribarra.lab1TINGESO.controllers;

import fernandoIribarra.lab1TINGESO.entities.RepairEntity;
import fernandoIribarra.lab1TINGESO.services.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/repair")
@CrossOrigin("*")
public class RepairController {
    @Autowired
    RepairService repairService;

    @GetMapping("/{id}")
    public ResponseEntity<RepairEntity> getRepairById(@PathVariable Long id) {
        RepairEntity repair = repairService.getRepairById(id);
        return ResponseEntity.ok(repair);
    }

    @GetMapping("/")
    public ResponseEntity<List<RepairEntity>> getAllRepairs() {
        List<RepairEntity> repairs = repairService.getAllRepairs();
        return ResponseEntity.ok(repairs);
    }

    @GetMapping("/{id}/calculate")
    public ResponseEntity<RepairEntity> calculateRepairTotalAmount(@PathVariable Long id) {
        RepairEntity repair = repairService.getRepairById(id);
        if (repair.getTotalAmount() > 0L) {
            return ResponseEntity.ok(repair);
        }
        repair = repairService.calculateTotalCost(repair);
        return ResponseEntity.ok(repair);
    }

    @PostMapping("/")
    public ResponseEntity<RepairEntity> saveRepair(@RequestBody RepairEntity repair) {
        RepairEntity newRepair = repairService.saveRepair(repair);
        return ResponseEntity.ok(newRepair);
    }

    @PutMapping("/")
    public ResponseEntity<RepairEntity> updateRepair(@RequestBody RepairEntity repair) {
        RepairEntity updatedRepair = repairService.updateRepair(repair);
        return ResponseEntity.ok(updatedRepair);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteRepair(@PathVariable Long id) throws Exception {
        var isDeleted = repairService.deleteRepair(id);
        return ResponseEntity.noContent().build();
    }
}
