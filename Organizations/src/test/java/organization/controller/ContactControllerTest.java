package organization.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import organization.dto.ContactDto;
import organization.entity.Organization;
import organization.service.ContactService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ContactController.class)

public class ContactControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ContactService contactService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @InjectMocks
    private ContactController contactController;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllContacts() throws Exception {
        Organization innobitlab = new Organization();
        List<ContactDto> contactDtoList = new ArrayList<>();
        contactDtoList.add(new ContactDto(1L,"GianGiacomo","Programmatore",innobitlab));
        contactDtoList.add(new ContactDto(2L,"Giovanni","Programmatore",innobitlab));
        when(contactService.getAllContacts()).thenReturn(contactDtoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/contact"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("GianGiacomo"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].job").value("Programmatore"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Giovanni"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].job").value("Programmatore"));

    }
    @Test
    public void testGetContact() throws Exception {
        Organization innobitlab = new Organization();
        ContactDto contactDto = new ContactDto(1L,"Giovanni","Programmatore",innobitlab);
        when(contactService.getContact(1L)).thenReturn(Optional.of(contactDto));
        mockMvc.perform(MockMvcRequestBuilders.get("/contact/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Giovanni"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.job").value("Programmatore"));
    }
    @Test
    public void testDeleteContact() throws Exception {
        Organization innobitlab = new Organization();
        ContactDto contactDto = new ContactDto(1L,"Giovanni","Programmatore",innobitlab);
        when(contactService.deleteContact(1L)).thenReturn(contactDto);
        mockMvc.perform(MockMvcRequestBuilders.delete("/contact/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(contactService, times(1)).deleteContact(1L);
    }
    @Test
    void testUpdateContact() {
        Long id = 1L;
        ContactDto contact = new ContactDto();
        contact.setId(id);

        when(contactService.updateContact(id, contact)).thenReturn(Optional.of(contact));

        ResponseEntity<ContactDto> response = contactController.updateContact(contact, id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(contact, response.getBody());
    }
    @Test
    public void testAddContact() throws Exception {
        Organization innobitlab = new Organization(1L,"innobitlab","Via Roma");
        ContactDto contactDto = new ContactDto(1L,"Giovanni","Programmatore",innobitlab);


        mockMvc.perform(MockMvcRequestBuilders.post("/contact")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contactDto)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(contactDto.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(contactDto.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.job").value(contactDto.getJob()));

        verify(contactService, times(1)).createContact(any(ContactDto.class));
    }

}



