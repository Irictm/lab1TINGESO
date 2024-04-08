package fernandoIribarra.lab1TINGESO.services;

import fernandoIribarra.lab1TINGESO.entities.BonusEntity;
import fernandoIribarra.lab1TINGESO.repositories.BonusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BonusService {
    @Autowired
    BonusRepository bonusRepository;

    public BonusEntity saveBonus(BonusEntity bonus) { return bonusRepository.save(bonus); }

    public BonusEntity getBonusById(Long id) { return bonusRepository.findById(id).get(); }

    public BonusEntity updateBonus(BonusEntity bonus) { return bonusRepository.save(bonus); }

    public boolean deleteBonus(Long id) throws Exception {
        try {
            bonusRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
