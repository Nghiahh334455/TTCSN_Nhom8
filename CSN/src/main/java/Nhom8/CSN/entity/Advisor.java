package Nhom8.CSN.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Advisor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ten;

    @OneToMany(mappedBy = "advisor") 
    private List<Classes> classes; 

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Classes> getClasses() { 
        return classes;
    }

    public void setClasses(List<Classes> classes) {
        this.classes = classes;
    }
}
