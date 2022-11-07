package com.example.demo.service;

import com.example.demo.model.Contact;
import com.example.demo.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public boolean saveMessageDetails(Contact contact){
        contact.setStatus("OPEN");
        Contact savedContact = contactRepository.save(contact);

        boolean isSaved = false;
        if(savedContact != null && savedContact.getContact_id() > 0){
             isSaved = true;
        }

        log.info(contact.toString());
        return isSaved;
    }

    public List<Contact> findMessagesWithOpenStatus(String status){
        return contactRepository.findByStatus(status);
    }

    public void updateMsgStatus(int id) {
        Contact contact = contactRepository.findById(id).get();
        if(Objects.nonNull(contact)){
            contact.setStatus("CLOSED");
        }
        contactRepository.save(contact);
    }
}
