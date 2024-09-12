package organization.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import organization.dto.ContactDto;
import organization.entity.Contact;
import organization.entity.Organization;
import organization.mapper.ContactMapper;
import organization.repository.ContactRepository;
import organization.repository.OrganizationRepository;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ContactServiceTest {
    @Mock
    private ContactRepository contactRepository;
    @Mock
    private OrganizationRepository organizationRepository;
    @Mock
    private ContactMapper contactMapper;
    @InjectMocks
    private ContactService contactService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testCreateContact() throws Exception {
        Organization innobitlab = new Organization(1L,"innobitlab","Via Roma");
        organizationRepository.save(innobitlab);
        Contact contact = new Contact(null,"Giovanni","Programmatore",innobitlab);
        ContactDto contactDto = new ContactDto(null,"Giovanni","Programmatore",innobitlab);

        when(contactMapper.dtoToEntity(any(ContactDto.class))).thenReturn(contact);

        contactService.createContact(contactDto);

        verify(contactMapper).dtoToEntity(contactDto);
        verify(contactRepository).save(contact);
    }
    @Test
    public void testGetContact() throws Exception {
        Organization innobitlab = new Organization(1L,"innobitlab","Via Roma");
        organizationRepository.save(innobitlab);
        Contact contact = new Contact(null,"Giovanni","Programmatore",innobitlab);
        contactRepository.save(contact);
        contactService.getContact(contact.getId());
        verify(contactRepository).findById(contact.getId());
    }
    @Test
    public void testGetAllContacts() throws Exception {
        Organization innobitlab = new Organization(1L,"innobitlab","Via Roma");
        organizationRepository.save(innobitlab);
        Contact contact = new Contact(null,"Giovanni","Programmatore",innobitlab);
        Contact contact1 = new Contact(null,"Giacomo","Programmatore",innobitlab);
        contactRepository.save(contact);
        contactRepository.save(contact1);
        contactService.getAllContacts();
        verify(contactRepository).findAll();
    }
    @Test
    public void testDeleteContact() throws Exception {
        Organization innobitlab = new Organization(1L, "innobitlab", "Via Roma");
        organizationRepository.save(innobitlab);
        Contact contact = new Contact(null, "Giovanni", "Programmatore", innobitlab);
        contactRepository.save(contact);

        contactService.deleteContact(contact.getId());

        Optional<Contact> deletedContact = contactRepository.findById(contact.getId());
        assertTrue(deletedContact.isEmpty());
    }
    @Test
    public void testUpdateContact() throws Exception {
        Organization innobitlab = new Organization(1L, "innobitlab", "Via Roma");
        organizationRepository.save(innobitlab);
        Contact contact = new Contact(null, "Giovanni", "Programmatore", innobitlab);
        contactRepository.save(contact);
        ContactDto contactDto =new ContactDto(null,"Giacomo", "Programmatore", innobitlab);

        contactService.updateContact(1L,contactDto);

        verify(contactMapper).dtoToEntity(contactDto);
        verify(contactRepository).save(contact);
        assertEquals("Giacomo",contactDto.getName());
    }



}
