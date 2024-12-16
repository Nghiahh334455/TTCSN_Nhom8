package Nhom8.CSN.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String className;  // Tên lớp học

    @OneToMany(mappedBy = "lop")  // Mỗi lớp có thể có nhiều sinh viên
    private List<Student> students;

    @ManyToOne(fetch = FetchType.LAZY)  // Mỗi lớp có một cố vấn
    @JoinColumn(name = "advisor_id")
    private Advisor advisor;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Advisor getAdvisor() {
        return advisor;
    }

    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
    }
}
