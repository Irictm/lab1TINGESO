package fernandoIribarra.lab1TINGESO.controllers;

import fernandoIribarra.lab1TINGESO.entities.BonusEntity;
import fernandoIribarra.lab1TINGESO.services.BonusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/bonus")
@CrossOrigin("*")
public class BonusController {
    @Autowired
    BonusService bonusService;

    @GetMapping("/{id}")
    public ResponseEntity<BonusEntity> getBonusById(@PathVariable Long id) {
        BonusEntity bonus = bonusService.getBonusById(id);
        return ResponseEntity.ok(bonus);
    }

    @PostMapping("/")
    public ResponseEntity<BonusEntity> saveBonus(@RequestBody BonusEntity bonus) {
        BonusEntity newBonus = bonusService.saveBonus(bonus);
        return ResponseEntity.ok(newBonus);
    }

    @PutMapping("/")
    public ResponseEntity<BonusEntity> updateBonus(@RequestBody BonusEntity bonus) {
        BonusEntity updatedBonus = bonusService.updateBonus(bonus);
        return ResponseEntity.ok(updatedBonus);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteBonus(@PathVariable Long id) throws Exception {
        var isDeleted = bonusService.deleteBonus(id);
        return ResponseEntity.noContent().build();
    }
}
