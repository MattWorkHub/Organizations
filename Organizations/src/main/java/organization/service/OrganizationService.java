package organization.service;

import org.springframework.beans.factory.annotation.Autowired;
import organization.dto.OrganizationDto;
import organization.entity.Organization;
import organization.mapper.OrganizationMapper;
import organization.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private OrganizationMapper organizationMapper;

    public void createOrganization(OrganizationDto organizationdto){
        Organization organization = organizationMapper.dtoToEntity(organizationdto);
        organizationRepository.save(organization);
    }
    public Optional<OrganizationDto> getOrganization(Long id){
        Optional<Organization> organizationOptional = organizationRepository.findById(id);
        if (organizationOptional.isPresent()) {
            OrganizationDto organizationDto = organizationMapper.entityToDto(organizationOptional.get());
            return Optional.of(organizationDto);
        } else {
            return Optional.empty();
        }

    }
    public List<OrganizationDto> getAllOrganizations(){
        List<Organization> organizations = organizationRepository.findAll();
        List<OrganizationDto> organizationDtos = new ArrayList<>();
        for(Organization organization : organizations){
            organizationDtos.add(organizationMapper.entityToDto(organization));
        }
        return organizationDtos;
    }
    public Optional<OrganizationDto> updateOrganization(Long id, OrganizationDto organizationDto) {
        Organization organization = organizationMapper.dtoToEntity(organizationDto);
        Optional<Organization> organizationToUpdate = organizationRepository.findById(id);
        if (organizationToUpdate.isPresent()) {
            organizationToUpdate.get().setName(organization.getName());
            organizationToUpdate.get().setAddress(organization.getAddress());
            Organization saved = organizationRepository.save(organizationToUpdate.get());
            return Optional.of(organizationMapper.entityToDto(saved));
        } else {
            return Optional.empty();
        }
    }
    public OrganizationDto deleteOrganization(Long id){
        organizationRepository.deleteById(id);
        return null;
    }
}
