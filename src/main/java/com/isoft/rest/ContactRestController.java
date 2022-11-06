package com.isoft.rest;

import com.isoft.models.Contact;
import com.isoft.models.Response;
import com.isoft.repositories.ContactRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/contact", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@CrossOrigin(origins = "*")
public class ContactRestController {
    private final ContactRepository contactRepository;

    public ContactRestController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping("/getMessagesByStatus")
    public List<Contact> getAllByStatus(@RequestParam String status) {
        return contactRepository.getAllByStatus(status);
    }

    @GetMapping("/getAllByCreatedBy")
    public List<Contact> getAllByCreatedBy(@RequestBody Contact contact) {
        return contactRepository.getAllByCreatedBy(contact.getCreatedBy());
    }

    @PostMapping("saveMessage")
    public ResponseEntity<Response> saveMessage(@Valid @RequestBody Contact contact) {
        if (contact != null)
            contactRepository.save(contact);
        ResponseEntity<Response> response = ResponseEntity
                .status(HttpStatus.CREATED)
                .header("edited-agent", "user agent is Now GCC")
                .body(new Response("Message saved."));
        return response;
    }

    @DeleteMapping("deleteMessage")
    public ResponseEntity<Response> deleteMessage(RequestEntity<Contact> requestEntity) {
        Contact contact = requestEntity.getBody();
        try {
            contactRepository.deleteById(contact.getId());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new Response("Entity deleted"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new Response("Contact with provided id not found"));
        }
    }

    @PatchMapping("updateMessage")
    public ResponseEntity<Response> updateMessage(@Valid @RequestBody Contact contact) {
        Optional<Contact> optional = contactRepository.findById(contact.getId());
        if (optional.isPresent()) {
            contactRepository.save(contact);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new Response("message updated"));
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new Response("operation failed"));


    }
}

