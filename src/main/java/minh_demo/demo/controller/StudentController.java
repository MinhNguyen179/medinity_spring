package minh_demo.demo.controller;

import minh_demo.demo.model.Student;
import minh_demo.demo.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepo repo;

    @PostMapping("/addStudent")
    public void addStudent(@RequestBody Student student) {
        repo.save(student);
    }

    @GetMapping("/listStudents")
    public List<Student> listStudent() {
        return repo.findAll();
    }
    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        try {
            repo.deleteById(id);
            return new ResponseEntity<>("Student deleted successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete the student.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
