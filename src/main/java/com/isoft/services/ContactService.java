package com.isoft.services;

import com.isoft.models.Contact;
import com.isoft.repositories.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//@ApplicationScope
public class ContactService {

    public static final String STATUS_OPEN = "open";
    public static final String STATUS_CLOSED = "closed";
    private final ContactRepository repository;

    public ContactService(ContactRepository repository) {
        this.repository = repository;
    }

    public boolean saveContactDetail(Contact contact) {
//        contact.setCreatedAt(LocalDateTime.now());
//        contact.setCreatedBy(WebSecurityConfigure.ANONYMOUS_ROLE);
//        int result=repository.saveContactMessage(contact);
        Contact savedContact = repository.save(contact);
        return savedContact != null && savedContact.getId() > 0;
    }

    public List<Contact> getOpenMessages() {
//        return repository.getMessages(STATUS_OPEN);
        return repository.findAllByStatusEquals(STATUS_OPEN);
    }

    public boolean closeMessage(int id/*,String updatedBy*/) {
//       return repository.closeMessage(id,createdBy) > 0 ;
        Optional<Contact> received = repository.findById(id);
        received.ifPresent(contact -> {
//            contact.setUpdatedAt(LocalDateTime.now());
//            contact.setUpdatedBy(updatedBy);
            contact.setStatus(STATUS_CLOSED);
        });
        Contact contact = repository.save(received.get());
        return contact != null && contact.getId() > 0;
    }
}
