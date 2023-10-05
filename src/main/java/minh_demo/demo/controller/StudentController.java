package minh_demo.demo.controller;

import minh_demo.demo.model.Student;
import minh_demo.demo.service.StudentService;
import minh_demo.demo.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepo repo;

    @Autowired
    StudentService  studentService;
    @PostMapping("/addStudent")
    public void addStudent(@RequestBody Student student) {
        repo.save(student);
    }
    @GetMapping("/listStudents")
    public String listStudent(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "minh";
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
