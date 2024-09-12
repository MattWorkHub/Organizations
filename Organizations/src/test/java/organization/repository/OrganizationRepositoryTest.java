package organization.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import organization.entity.Organization;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrganizationRepositoryTest {
    @Autowired
    private OrganizationRepository organizationRepository;

    @Test
    public void testFindOrganization() {
        Organization organization = new Organization(null,"Innobitlab","Via Roma");
        organizationRepository.save(organization);
        Organization savedOrganization = organizationRepository.findById(organization.getId()).get();
        assertNotNull(savedOrganization);
        assertEquals("Innobitlab",savedOrganization.getName());
    }
    @Test
    public void testFindAllOrganizations() {
        Organization organization = new Organization(null,"Innobitlab","Via Roma");
        Organization organization1 = new Organization(null,"Malda","Via Roma");
        organizationRepository.save(organization);
        organizationRepository.save(organization1);
        List<Organization> organizations = organizationRepository.findAll();
        assertNotNull(organizations);
        assertEquals(2, organizations.size());
        assertEquals("Innobitlab",organizations.get(0).getName());
        assertEquals("Malda",organizations.get(1).getName());
    }
    @Test
    public void testDeleteOrganization() {
        Organization organization = new Organization(null,"Innobitlab","Via Roma");
        organizationRepository.save(organization);
        organizationRepository.deleteById(organization.getId());
        Optional<Organization> deletedOrganization = organizationRepository.findById(organization.getId());
        assertTrue(deletedOrganization.isEmpty());
    }
    @Test
    public void testSaveOrganization() {
        Organization organization = new Organization(null,"Innobitlab","Via Roma");
        organizationRepository.save(organization);
        Organization savedOrganization = organizationRepository.findById(organization.getId()).get();
        assertNotNull(savedOrganization);
        assertEquals("Innobitlab",savedOrganization.getName());
    }
    @Test
    public void testUpdateOrganization() {
        Organization organization = new Organization(null,"Innobitlab","Via Roma");
        organizationRepository.save(organization);
        organization.setName("Malda");
        organizationRepository.save(organization);
        Organization savedOrganization = organizationRepository.findById(organization.getId()).get();
        assertEquals("Malda",savedOrganization.getName());

    }
}
