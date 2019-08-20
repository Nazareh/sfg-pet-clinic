package turmina.velho.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import turmina.velho.sfgpetclinic.model.Owner;
import turmina.velho.sfgpetclinic.services.OwnerService;

import java.util.List;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }


    @RequestMapping({"/find"})
    public String findOwners (Model model){

        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @RequestMapping
    public String processFindForm(Owner owner, BindingResult result, Model model){
        //allow parameterless GET request for /owners to return all records
        if (owner.getLastName() == null){
            owner.setLastName(""); //empty string simplifies broadest possible search
        }

        //find owners by last name
        List<Owner> ownerList = ownerService.findAllByLastNameLike(owner.getLastName());

        if (ownerList.isEmpty()) {
            result.rejectValue("lastName","notFound","not found");
            return "redirect:/owners/findOwners";
        } else if (ownerList.size() == 1 ){
            owner = ownerList.iterator().next();
            return "redirect:/owner/" + owner.getId();
        }else {
            model.addAttribute("selections",ownerList);
            return "owners/ownerList";
        }

    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId){
        ModelAndView modelAndView = new ModelAndView("owners/ownerDetails");
        modelAndView.addObject(ownerService.findById(ownerId));
        return modelAndView;
    }
}
