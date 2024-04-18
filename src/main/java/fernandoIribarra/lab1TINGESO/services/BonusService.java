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

    public BonusEntity getBonusByBrand(String brand) { return bonusRepository.findByBrand(brand); }

    public BonusEntity updateBonus(BonusEntity bonus) { return bonusRepository.save(bonus); }

    public boolean deleteBonus(Long id) throws Exception {
        try {
            bonusRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    public Long consumeBonus(String brand){
        long bonusDiscount = 0;
        BonusEntity bonus = getBonusByBrand(brand);
        if (bonus.getNumberRemaining() > 0) {
            bonus.setNumberRemaining(bonus.getNumberRemaining()-1);
            bonusDiscount = bonus.getAmount();
            updateBonus(bonus);
        }
        return bonusDiscount;
    }
}
