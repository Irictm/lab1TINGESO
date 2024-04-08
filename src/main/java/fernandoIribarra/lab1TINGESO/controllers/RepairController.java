package fernandoIribarra.lab1TINGESO.controllers;

import fernandoIribarra.lab1TINGESO.services.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/repair")
@CrossOrigin("*")
public class RepairController {
    @Autowired
    RepairService repairService;
}
