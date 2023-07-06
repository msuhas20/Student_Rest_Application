package net.javanew.springbootbackend.repository;

import net.javanew.springbootbackend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;



public interface StudentRepository extends JpaRepository<Student, Long> {
}
