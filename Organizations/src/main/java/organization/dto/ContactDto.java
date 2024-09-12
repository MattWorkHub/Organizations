package organization.dto;


import organization.entity.Organization;

public class ContactDto {
    private Long id;
    private String name;
    private String job;
    private Organization organization;

    public ContactDto() {
    }
    public ContactDto(Long id, String name, String job, Organization organization) {
        this.id = id;
        this.name = name;
        this.job = job;
        this.organization = organization;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
