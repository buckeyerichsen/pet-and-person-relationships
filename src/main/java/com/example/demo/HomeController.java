package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    PetRepository petRepository;


    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("pets", petRepository.findAll());
        model.addAttribute("persons", personRepository.findAll());
        return "index";
    }

    @GetMapping("/personform")
    public String personForm(Model model){
        model.addAttribute("persons", new Person());
        return "personform";
    }
    @GetMapping("/petform")
    public String petForm(Model model){
        model.addAttribute("pet", new Pet());
        model.addAttribute("person", personRepository.findAll());
        return "petform";
    }
    @PostMapping("/processperson")
    public String processPerson(@Valid Person persons,
                                BindingResult result){
        if (result.hasErrors()){
            return "personform";
        }
        personRepository.save(persons);
        return "redirect:/";
    }
    @PostMapping("/petform")
    public String processPet(@Valid Pet pet,
                             BindingResult result){
        if (result.hasErrors()){
            return "petform";
        }
        petRepository.save(pet);
        return "redirect:/";
    }
    @RequestMapping("/petlist")
    public String petList(Model model){
        model.addAttribute("pets", petRepository.findAll());
        return "petlist";
    }
    @RequestMapping("/personlist")
    public String personList(Model model){
        model.addAttribute("persons", personRepository.findAll());
        return "personlist";

    }
    @RequestMapping("/detailperson/{id}")
    public String showPerson(@PathVariable("id") long id, Model model){
        model.addAttribute("persons", personRepository.findById(id));
        return "personform";
    }
    @RequestMapping("/updateperson/{id}")
    public String updatePerson(@PathVariable("id") long id, Model model){
        model.addAttribute("persons", personRepository.findById(id));
        return "personform";
    }
    @RequestMapping("/deleteperson/{id}")
    public String delPerson(@PathVariable("id") long id){
        personRepository.deleteById(id);
        return "redirect:/";
    }
    @RequestMapping("/detailpet/{id}")
    public String showPet(@PathVariable("id") long id, Model model){
        model.addAttribute("pet", petRepository.findById(id));
        return "petform";
    }
    @RequestMapping("/updatepet/{id}")
    public String updatePet(@PathVariable("id") long id, Model model){
        model.addAttribute("pet", petRepository.findById(id));
        return "petform";
    }
    @RequestMapping("/deletepet/{id}")
    public String delPet(@PathVariable("id") long id){
        petRepository.deleteById(id);
        return "redirect:/";
    }
    @PostMapping("/processsearch")
    public String searchResult(Model model, @RequestParam(name="search") String search){
        model.addAttribute("pets", petRepository.findByName(search));
        model.addAttribute("persons", personRepository.findByName(search));
        return "searchlist";
    }



}
