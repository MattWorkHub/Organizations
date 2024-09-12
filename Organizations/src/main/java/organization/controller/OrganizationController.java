package organization.controller;

import organization.dto.OrganizationDto;
import organization.entity.Organization;
import organization.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/organization")

public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;
    @PostMapping("")
    public ResponseEntity<OrganizationDto> addOrganization(@RequestBody OrganizationDto organization){
        organizationService.createOrganization(organization);
        return ResponseEntity.ok().body(organization);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<OrganizationDto>> getOrganization(@PathVariable Long id
    ){
        Optional<OrganizationDto> organizationOptional = organizationService.getOrganization(id);
        return ResponseEntity.ok().body(organizationOptional);
    }
    @GetMapping("")
    public ResponseEntity<List<OrganizationDto>> getAllOrganizations(){
        List<OrganizationDto> organizations = organizationService.getAllOrganizations();
        return ResponseEntity.ok().body(organizations);
    }
    @PutMapping("/{id}")
    public ResponseEntity<OrganizationDto> updateOrganization(@RequestBody OrganizationDto organizationDto,@PathVariable Long id){
        Optional<OrganizationDto> organizationOptional = organizationService.updateOrganization(id,organizationDto);
        if (organizationOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(organizationDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<OrganizationDto> deleteOrganization(@PathVariable Long id){
        OrganizationDto organizationToDelete = organizationService.deleteOrganization(id);
        return ResponseEntity.ok().body(organizationToDelete);
    }

}
