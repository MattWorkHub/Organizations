package organization.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import organization.entity.Contact;
import organization.entity.Organization;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ContactRepositoryTest {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private OrganizationRepository organizationRepository;
    @Test
    public void testSaveContact() {
        Organization innobitlab = new Organization();
        organizationRepository.save(innobitlab);
        Contact contact = new Contact(null,"Giovanni","Programmatore",innobitlab);
        Contact savedContact = contactRepository.save(contact);
        assertNotNull(savedContact);
        assertEquals(savedContact.getName(),contact.getName());
    }
    @Test
    public void testFindById() {
        Organization innobitlab = new Organization();
        organizationRepository.save(innobitlab);
        Contact contact = new Contact(null,"Giovanni","Programmatore",innobitlab);
        contactRepository.save(contact);
        Contact savedContact = contactRepository.findById(contact.getId()).get();
        assertNotNull(savedContact);
        assertEquals(savedContact.getName(),contact.getName());
    }
    @Test
    public void testFindAll(){
        Organization innobitlab = new Organization();
        organizationRepository.save(innobitlab);
        contactRepository.save(new Contact(null,"Giovanni","Programmatore",innobitlab));
        contactRepository.save(new Contact(null,"Giacomo","Programmatore",innobitlab));
        List<Contact> contacts = contactRepository.findAll();
        assertNotNull(contacts);
        assertEquals(contacts.size(),2);
    }
    @Test
    public void testDeleteContact() {
        Organization innobitlab = new Organization();
        organizationRepository.save(innobitlab);
        Contact contact = new Contact(null,"Giovanni","Programmatore",innobitlab);
        contactRepository.save(contact);
        contactRepository.deleteById(contact.getId());
        Optional<Contact> deletedContact = contactRepository.findById(contact.getId());
        assertTrue(deletedContact.isEmpty());
    }
    @Test
    public void testUpdateContact() {
        Organization innobitlab = new Organization();
        organizationRepository.save(innobitlab);
        Contact contact = new Contact(null,"Giovanni","Programmatore",innobitlab);
        contactRepository.save(contact);
        contact.setJob("Assurdo");
        contactRepository.save(contact);
        Contact updatedContact = contactRepository.findById(contact.getId()).get();
        assertEquals(updatedContact.getJob(),"Assurdo");
    }
}
