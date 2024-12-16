package Nhom8.CSN.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Result")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double diemTX1; // Điểm thường xuyên 1
    private Double diemTX2; // Điểm thường xuyên 2
    private Double diemGK;  // Điểm giữa kỳ
    private Double diemThi; // Điểm thi

    private Double diemTongKet; // Điểm tổng kết
    private Double diemHe4;     // Điểm hệ 4
    private String xepLoai;     // Xếp loại

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student; // Quan hệ với Student

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDiemTX1() {
        return diemTX1;
    }

    public void setDiemTX1(Double diemTX1) {
        this.diemTX1 = diemTX1;
        calculateDerivedFields(); // Tính toán lại khi thay đổi
    }

    public Double getDiemTX2() {
        return diemTX2;
    }

    public void setDiemTX2(Double diemTX2) {
        this.diemTX2 = diemTX2;
        calculateDerivedFields(); // Tính toán lại khi thay đổi
    }

    public Double getDiemGK() {
        return diemGK;
    }

    public void setDiemGK(Double diemGK) {
        this.diemGK = diemGK;
        calculateDerivedFields(); // Tính toán lại khi thay đổi
    }

    public Double getDiemThi() {
        return diemThi;
    }

    public void setDiemThi(Double diemThi) {
        this.diemThi = diemThi;
        calculateDerivedFields(); // Tính toán lại khi thay đổi
    }

    public Double getDiemTongKet() {
        return diemTongKet;
    }

    public void setDiemTongKet(Double diemTongKet) {
        this.diemTongKet = diemTongKet;
    }

    public Double getDiemHe4() {
        return diemHe4;
    }

    public void setDiemHe4(Double diemHe4) {
        this.diemHe4 = diemHe4;
    }

    public String getXepLoai() {
        return xepLoai;
    }

    public void setXepLoai(String xepLoai) {
        this.xepLoai = xepLoai;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    // Tính toán lại điểm tổng kết, điểm hệ 4, và xếp loại
    private void calculateDerivedFields() {
        if (diemTX1 != null && diemTX2 != null && diemGK != null && diemThi != null) {
            diemTongKet = diemTX1 * 0.1 + diemTX2 * 0.1 + diemGK * 0.2 + diemThi * 0.6;
            diemHe4 = convertToHe4(diemTongKet);
            xepLoai = determineXepLoai(diemHe4);
        } else {
            diemTongKet = null;
            diemHe4 = null;
            xepLoai = null;
        }
    }

    private Double convertToHe4(Double diemTK) {
        if (diemTK < 4.0) return 0.0;
        if (diemTK <= 4.6) return 1.0;
        if (diemTK <= 5.4) return 1.5;
        if (diemTK <= 6.2) return 2.0;
        if (diemTK <= 6.9) return 2.5;
        if (diemTK <= 7.6) return 3.0;
        if (diemTK <= 8.4) return 3.5;
        return 4.0;
    }

    private String determineXepLoai(Double diemHe4) {
        if (diemHe4 == 0.0) return "F";
        if (diemHe4 == 1.0) return "D";
        if (diemHe4 == 1.5) return "D+";
        if (diemHe4 == 2.0) return "C";
        if (diemHe4 == 2.5) return "C+";
        if (diemHe4 == 3.0) return "B";
        if (diemHe4 == 3.5) return "B+";
        return "A";
    }
}
