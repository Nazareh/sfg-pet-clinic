package turmina.velho.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import turmina.velho.sfgpetclinic.model.Pet;
import turmina.velho.sfgpetclinic.model.Visit;
import turmina.velho.sfgpetclinic.services.PetService;
import turmina.velho.sfgpetclinic.services.VisitService;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Controller
@RequestMapping("/owners/{ownerId}/pets/{petId}/visits")
public class VisitController {

    private final VisitService visitService;
    private final PetService petService;

    public VisitController(VisitService visitService, PetService petService) {
        this.visitService = visitService;
        this.petService = petService;
    }

    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable("petId") Long petId, Map<String, Object> model){
        Pet pet = petService.findById(petId);
        model.put("pet",pet);
        Visit visit = new Visit();
        pet.getVisits().add(visit);
        visit.setPet(pet);

        return visit;
    }

    @InitBinder
    public void initPetBinder (WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");

        dataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException{
                setValue(LocalDate.parse(text));
            }
        });
    }

    @GetMapping("/new")
    // Spring MVC calls method loadPetWithVisit(...) before initNewVisitForm is called
    public String initNewVisitForm(@PathVariable("petId") Long petId, Map<String, Object> model) {
        return "pets/createOrUpdateVisitForm";
    }

    // Spring MVC calls method loadPetWithVisit(...) before processNewVisitForm is called
    @PostMapping("/new")
    public String processVisitForm (@Valid Visit visit, BindingResult result){
        if (result.hasErrors()){
            return "pets/createOrUpdateVisitForm";
        }else{
            visitService.save(visit);
            return "redirect:/owners/{ownerId}";
        }

    }
}
