package fernandoIribarra.lab1TINGESO.services;

import fernandoIribarra.lab1TINGESO.entities.ReceiptEntity;
import fernandoIribarra.lab1TINGESO.repositories.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiptService {
    @Autowired
    ReceiptRepository receiptRepository;

    public ReceiptEntity saveReceipt(ReceiptEntity receipt) { return receiptRepository.save(receipt);}

    public ReceiptEntity getReceiptById(Long id) { return receiptRepository.findById(id).get(); }

    public ReceiptEntity updateReceipt(ReceiptEntity receipt) { return receiptRepository.save(receipt); }

    public boolean deleteReceipt(Long id) throws Exception {
        try {
            receiptRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
