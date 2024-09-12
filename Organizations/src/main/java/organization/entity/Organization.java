package organization.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    @OneToMany(mappedBy = "organization", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("organization")
    private List<Contact> contactList;

    public Organization() {
    }

    public Organization(Long id, String name, String address) {
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
    @JsonIgnore
    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }
}
