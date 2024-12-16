package Nhom8.CSN.repository;

import Nhom8.CSN.entity.Classes;
import Nhom8.CSN.entity.Student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findById(long id); // Timf sinh viên theo idid
    List<Student> findByLop(Classes lop);  // Tìm sinh viên theo lớp
}
