package organization.dto;

import organization.entity.Contact;

import java.util.ArrayList;
import java.util.List;

public class OrganizationDto {
    private Long id;
    private String name;
    private String address;
    private List<Contact> contactList;

    public OrganizationDto() {
    }

    public OrganizationDto(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contactList = new ArrayList<>();
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }
}
