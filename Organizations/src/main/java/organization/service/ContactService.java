package organization.service;

import org.springframework.beans.factory.annotation.Autowired;
import organization.dto.ContactDto;
import organization.entity.Contact;
import organization.mapper.ContactMapper;
import organization.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService  {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ContactMapper contactMapper;

    public void createContact(ContactDto contactDto) {
        Contact contact = contactMapper.dtoToEntity(contactDto);
        contactRepository.save(contact);
    }
    public Optional<ContactDto> getContact(Long id) {
        Optional<Contact> contact = contactRepository.findById(id);
        return contact.map(contactMapper::entityToDto);
    }
    public List<ContactDto> getAllContacts(){
        List<Contact> contacts = contactRepository.findAll();
        List<ContactDto> contactsDto = new ArrayList<>();
        for (Contact contact : contacts) {
            contactsDto.add(contactMapper.entityToDto(contact));
        }
        return contactsDto;
    }
    public Optional<ContactDto> updateContact(Long id,ContactDto contactDto){
        Contact contact = contactMapper.dtoToEntity(contactDto);
        Optional<Contact> contactToUpdate = contactRepository.findById(id);
        if (contactToUpdate.isPresent()){
            contactToUpdate.get().setName(contact.getName());
            contactToUpdate.get().setJob(contact.getJob());
            contactToUpdate.get().setOrganization(contact.getOrganization());
            Contact saved = contactRepository.save(contactToUpdate.get());
            return Optional.of(contactMapper.entityToDto(saved));
        } else {
            return Optional.empty();
        }
    }
    public ContactDto deleteContact (Long id){
        contactRepository.deleteById(id);
        return null;
    }
}
