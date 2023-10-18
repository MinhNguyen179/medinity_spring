package minh_demo.demo.controller;

import minh_demo.demo.dto.StudentDTO;
import minh_demo.demo.dto.StudentResponse;
import minh_demo.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class StudentController {

    @Autowired
    StudentService  studentService;

    @GetMapping("/listStudents")
    public ResponseEntity<StudentResponse> listStudent(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return new ResponseEntity<>(studentService.getAllStudent(pageNo, pageSize), HttpStatus.OK);
    }
    @GetMapping("/student/{id}")
    public ResponseEntity<StudentDTO> studentDetail(@PathVariable int id) {
        return ResponseEntity.ok(studentService.getStudentById(id));    }
    @PostMapping("student/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        return new ResponseEntity<>(studentService.createStudent(studentDTO), HttpStatus.CREATED);
    }

    @PutMapping("student/{id}/update")
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO pokemonDto, @PathVariable("id") int pokemonId) {
        StudentDTO response = studentService.updateStudent(pokemonDto, pokemonId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("student/{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int pokemonId) {
        studentService.deleteStudent(pokemonId);
        return new ResponseEntity<>("Student delete", HttpStatus.OK);
    }
}
