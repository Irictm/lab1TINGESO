package fernandoIribarra.lab1TINGESO.controllers;

import fernandoIribarra.lab1TINGESO.services.BonusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/bonus")
@CrossOrigin("*")
public class BonusController {
    @Autowired
    BonusService bonusService;
}
