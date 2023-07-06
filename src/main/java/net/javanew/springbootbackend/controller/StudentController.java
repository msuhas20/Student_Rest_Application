package net.javanew.springbootbackend.controller;


import net.javanew.springbootbackend.exception.ResourceNotFoundException;
import net.javanew.springbootbackend.model.Student;
import net.javanew.springbootbackend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.logging.Logger;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }



    @PostMapping
    public Student createEmployee(@RequestBody Student student) {
        return studentRepository.save(student);
    }


    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable  long id){
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not exist with id:" + id));
        return ResponseEntity.ok(student);
    }


    @PutMapping("{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable long id,@RequestBody Student studentDetails) {

                Student updateStudent = studentRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Student not exist with id: " + id));

                updateStudent.setFirstName(studentDetails.getFirstName());
                updateStudent.setLastName(studentDetails.getLastName());

                studentRepository.save(updateStudent);

                return ResponseEntity.ok(updateStudent);

    }


    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable long id){

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not exist with id: " + id));

        studentRepository.delete(student);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
