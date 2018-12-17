package turmina.velho.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import turmina.velho.sfgpetclinic.services.OwnerService;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"","/","/index","/index.hmtl"})
    public String listOwners(Model model){

        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }
}
