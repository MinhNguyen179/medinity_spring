package minh_demo.demo.service;

import minh_demo.demo.dto.model.StudentDTO;
import minh_demo.demo.dto.response.StudentResponse;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {
    StudentDTO createStudent(StudentDTO pokemonDto);
    StudentResponse getAllStudent(int pageNo, int pageSize);
    StudentDTO getStudentById(int id);
    StudentDTO updateStudent(StudentDTO studentDto, int id);
    void deleteStudent(int id);
}
