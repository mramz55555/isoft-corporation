package com.isoft.services;

import com.isoft.conifg.AppProps;
import com.isoft.models.Contact;
import com.isoft.repositories.ContactRepository;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
//@ApplicationScope
public class ContactService {

    public static final String STATUS_OPEN = "open";
    public static final String STATUS_CLOSED = "closed";
    private final ContactRepository repository;
    //    @Value("${isoft.pageSize}")
    private int numberOfRecords;
    private AppProps appProps;
    private Environment environment;


    public ContactService(ContactRepository repository, AppProps appProps, Environment environment) {
        this.repository = repository;
        this.appProps = appProps;
        this.environment = environment;
    }

    @Transactional
    public boolean saveContactDetail(Contact contact) {
//        contact.setCreatedAt(LocalDateTime.now());
//        contact.setCreatedBy(WebSecurityConfigure.ANONYMOUS_ROLE);
//        int result=repository.saveContactMessage(contact);
        Contact savedContact = repository.save(contact);
        return savedContact != null && savedContact.getId() > 0;
    }

    @Transactional
    public Page<Contact> getOpenMessages(int pageNum, String sortField, String sortDir) {
//        System.out.println(environment.getProperty("isoft.pageSize"));
//        System.out.println(environment.getProperty("PROCESSOR_ARCHITECTURE"));
//        return repository.getMessages(STATUS_OPEN);
        numberOfRecords = appProps.getPageSize() > 0 ? appProps.getPageSize() : numberOfRecords;
        return repository.getAllByStatus(STATUS_OPEN, PageRequest.of(pageNum - 1, numberOfRecords,
                sortDir.equals("asc") ? Sort.by(sortField) : Sort.by(sortField).descending()));
    }

    @Transactional
    public boolean closeMessage(int id/*,String updatedBy*/) {
        return repository.deleteById(id) > 0;
//       return repository.closeMessage(id,createdBy) > 0 ;
//        Optional<Contact> received = repository.findById(id);
//        received.ifPresent(contact -> {
//            contact.setUpdatedAt(LocalDateTime.now());
//            contact.setUpdatedBy(updatedBy);
//            contact.setStatus(STATUS_CLOSED);
//        });
//        Contact contact = repository.save(received.get());
//        return contact != null && contact.getId() > 0;
    }
}
