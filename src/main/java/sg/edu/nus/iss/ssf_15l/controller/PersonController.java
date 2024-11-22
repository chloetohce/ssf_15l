package sg.edu.nus.iss.ssf_15l.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import sg.edu.nus.iss.ssf_15l.model.Person;
import sg.edu.nus.iss.ssf_15l.service.PersonService;






@Controller
@RequestMapping("/persons")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public String allPersons(Model model) {
        model.addAttribute("persons", personService.findAll("persons"));
        return "persons";
    }

    @GetMapping("/add")
    public String addPerson(Model model) {
        model.addAttribute("person", new Person());
        return "add-person";
    }
    
    @GetMapping("/delete/{id}")
    public String deletePerson(@PathVariable("id") String id) {
        personService.delete("persons", id);
        return "redirect:/persons";
    }

    @PostMapping("/add")
    public String postAddPerson(@Valid @ModelAttribute("person") Person person, BindingResult binding, Model model) {
        if (binding.hasErrors()) {
            return "add-person";
        }
        personService.addPerson("persons", person);
        
        return "redirect:/persons";
    }
    
}
