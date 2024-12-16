package Nhom8.CSN.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Student {
    @Id
    private long id;
    private String ten;
    private LocalDate ngaySinh;
    private String gioiTinh;
    private String phone;
    private String diaChi;
    private int tongSoTin;
    private double GPA;

    @ManyToOne(fetch = FetchType.LAZY)  // Mỗi sinh viên thuộc một lớp
    @JoinColumn(name = "class_id")
    private Classes lop;  // Lớp của sinh viên

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    // Chuyển từ OneToOne sang OneToMany
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Result> results;

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getTongSoTin() {
        return tongSoTin;
    }

    public void setTongSoTin(int tongSoTin) {
        this.tongSoTin = tongSoTin;
    }

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    public Classes getLop() {
        return lop;
    }

    public void setLop(Classes lop) {
        this.lop = lop;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
