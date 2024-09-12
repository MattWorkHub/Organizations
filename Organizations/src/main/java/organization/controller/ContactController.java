package organization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import organization.dto.ContactDto;
import organization.service.ContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    private ContactService contactService;


    @PostMapping("")
    public ResponseEntity<ContactDto> addContact(@RequestBody ContactDto contact){
        contactService.createContact(contact);
        return ResponseEntity.ok().body(contact);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<ContactDto>> getContact(@PathVariable Long id){
        Optional<ContactDto> contactOptional = contactService.getContact(id);
        return ResponseEntity.ok().body(contactOptional);
    }
    @GetMapping("")
    public ResponseEntity<List<ContactDto>> getAllContacts(){
        List<ContactDto> contacts = contactService.getAllContacts();
        return ResponseEntity.ok().body(contacts);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ContactDto> updateContact(@RequestBody ContactDto contact,@PathVariable Long id){
        Optional<ContactDto> contactOptional = contactService.updateContact(id,contact);
        if (contactOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(contact);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ContactDto> deleteContact(@PathVariable Long id){
        ContactDto contactToDelete = contactService.deleteContact(id);
        return ResponseEntity.ok().body(contactToDelete);
    }

}
