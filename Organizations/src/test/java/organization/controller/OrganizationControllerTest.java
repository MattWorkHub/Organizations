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
import organization.dto.OrganizationDto;
import organization.service.OrganizationService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = OrganizationController.class)
public class OrganizationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OrganizationService organizationService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @InjectMocks
    private OrganizationController organizationController;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllOrganizations() throws Exception {
        List<OrganizationDto> organizationDtoList = new ArrayList<>();
        organizationDtoList.add(new OrganizationDto(1L,"innobitlab","via Roma"));
        organizationDtoList.add(new OrganizationDto(2L,"Malda","via Roma"));
        when(organizationService.getAllOrganizations()).thenReturn(organizationDtoList);
        mockMvc.perform(MockMvcRequestBuilders.get("/organization"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("innobitlab"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].address").value("via Roma"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Malda"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].address").value("via Roma"));
    }
    @Test
    public void testGetOrganization() throws Exception {
        OrganizationDto organizationDto = new OrganizationDto(1L,"innobitlab","via Roma");
        when(organizationService.getOrganization(1L)).thenReturn(Optional.of(organizationDto));
        mockMvc.perform(MockMvcRequestBuilders.get("/organization/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("innobitlab"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("via Roma"));
    }
    @Test
    public void testDeleteOrganization() throws Exception {
        OrganizationDto organizationDto = new OrganizationDto(1L,"Malda","via Roma");
        when(organizationService.deleteOrganization(1L)).thenReturn(organizationDto);
        mockMvc.perform(MockMvcRequestBuilders.delete("/organization/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(organizationService).deleteOrganization(1L);
    }
    @Test
    public void testCreateOrganization() throws Exception {
        OrganizationDto organizationDto = new OrganizationDto(1L,"innobitlab","via Roma");

        mockMvc.perform(MockMvcRequestBuilders.post("/organization")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(organizationDto)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("innobitlab"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("via Roma"));
        verify(organizationService, times(1)).createOrganization(any(OrganizationDto.class));
    }
    @Test
    public void testUpdateOrganization() throws Exception {
        OrganizationDto organizationDto = new OrganizationDto(1L,"innobitlab","via Roma");

        when(organizationService.updateOrganization(1L,organizationDto)).thenReturn(Optional.of(organizationDto));

        ResponseEntity<OrganizationDto> response = organizationController.updateOrganization(organizationDto,organizationDto.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(organizationDto, response.getBody());

    }
}
