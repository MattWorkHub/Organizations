package organization.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import organization.dto.OrganizationDto;
import organization.entity.Organization;
import organization.mapper.OrganizationMapper;
import organization.repository.OrganizationRepository;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrganizationServiceTest {
    @Mock
    private OrganizationRepository organizationRepository;
    @Mock
    private OrganizationMapper organizationMapper;
    @InjectMocks
    private OrganizationService organizationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateOrganization() {
        Organization organization = new Organization(null,"Innobitlab","Via Roma");
        OrganizationDto organizationDto = new OrganizationDto(null,"Innobitlab","Via Roma");

        when(organizationMapper.dtoToEntity(any(OrganizationDto.class))).thenReturn(organization);

        organizationService.createOrganization(organizationDto);

        verify(organizationMapper).dtoToEntity(any(OrganizationDto.class));
        verify(organizationRepository).save(any(Organization.class));
    }
    /*@Test
    public void testUpdateOrganization() {
        Organization organization = new Organization(1L,"Innobitlab","Via Roma");
        OrganizationDto organizationDto = new OrganizationDto(1L,"Malda","Via Roma");

        when(organizationRepository.findById(1L)).thenReturn(Optional.of(organization));
        when(organizationMapper.dtoToEntity(any(OrganizationDto.class))).thenReturn(organization);
        when(organizationRepository.save(any(Organization.class))).thenReturn(organization);

        organizationRepository.save(organization);
        organizationService.updateOrganization(1L, organizationDto);

        verify(organizationMapper).dtoToEntity(any(OrganizationDto.class));
        verify(organizationRepository).save(any(Organization.class));
        assertEquals(organizationRepository.findById(1L).get().getName(),organizationDto.getName());


    }*/
    @Test
    public void testUpdateOrganization() {
        Organization organization = new Organization(1L, "Innobitlab", "Via Roma");
        OrganizationDto organizationDto = new OrganizationDto(1L, "Malda", "Via Roma");

        when(organizationRepository.findById(1L)).thenReturn(Optional.of(organization));
        when(organizationMapper.dtoToEntity(organizationDto)).thenReturn(organization);
        when(organizationRepository.save(organization)).thenReturn(organization);

        organizationService.updateOrganization(1L, organizationDto);

        verify(organizationMapper).dtoToEntity(any(OrganizationDto.class));
        verify(organizationRepository).save(any(Organization.class));

        assertEquals("Malda", organization.getName());
    }
}
