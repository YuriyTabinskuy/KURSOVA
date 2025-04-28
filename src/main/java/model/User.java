package model;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    @ElementCollection
    private List<String> preferredDates;  // Дати для вибору
    @ElementCollection
    private List<String> blockedDates;    // Блоковані дати

    // Геттери і сеттери
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getPreferredDates() {
        return preferredDates;
    }

    public void setPreferredDates(List<String> preferredDates) {
        this.preferredDates = preferredDates;
    }

    public List<String> getBlockedDates() {
        return blockedDates;
    }

    public void setBlockedDates(List<String> blockedDates) {
        this.blockedDates = blockedDates;
    }
}
