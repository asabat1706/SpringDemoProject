package com.example.demo.controller;

import com.example.demo.model.Contact;
import com.example.demo.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
public class ContactController {

    @Autowired
    private final ContactService contactService;

    public ContactController(ContactService contactService){
        this.contactService = contactService;
    }

    @RequestMapping(value = {"contact"})
    public String ProcesscontactPage(Model model){
        model.addAttribute("contact", new Contact());
        return "contact.html";
    }

    @PostMapping(value = {"saveMsg"})
    public String sendContactMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors){
        if(errors.hasErrors()){
            log.error("Contact form validation failed due to: "+ errors.getAllErrors());
            return "contact.html";
        }
        boolean saveSuccess = contactService.saveMessageDetails(contact);
        if(!saveSuccess){
            log.error("Contact form save failed");
            throw new RuntimeException("Couldn't send message... Try again");
        }
        return "redirect:/contact";
    }

    @GetMapping("/displayMessages")
    public ModelAndView displayMessages(Model model){
        List<Contact> openMessages = contactService.findMessagesWithOpenStatus("OPEN");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("messages", openMessages);
        modelAndView.setViewName("messages.html");
        return modelAndView;
    }

    @GetMapping("/closeMsg")
    public String closeOpenMessages(@RequestParam(name = "id") int id, Authentication authentication){
        contactService.updateMsgStatus(id,authentication.getName());
        return "redirect:/displayMessages";
    }
}
