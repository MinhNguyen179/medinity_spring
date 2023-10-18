package minh_demo.demo.service.implementation;

import minh_demo.demo.service.StudentService;
import minh_demo.demo.dto.StudentDTO;
import minh_demo.demo.dto.StudentResponse;
import minh_demo.demo.exceptions.StudentNotFoundException;
import minh_demo.demo.model.Student;
import minh_demo.demo.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImplement implements StudentService {
    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImplement(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setAge(studentDTO.getAge());

        Student newStudent = studentRepository.save(student);

        StudentDTO studentResponse = new StudentDTO();
        studentResponse.setId((int) newStudent.getId());
        studentResponse.setName(newStudent.getName());
        studentResponse.setAge(newStudent.getAge());
        return studentResponse;
    }

    @Override
    public StudentResponse getAllStudent(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Student> students = studentRepository.findAll(pageable);
        List<Student> listOfStudent = students.getContent();
        List<StudentDTO> content = listOfStudent.stream().map(p -> mapToDto(p)).collect(Collectors.toList());

        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setContent(content);
        studentResponse.setPageNo(students.getNumber());
        studentResponse.setPageSize(students.getSize());
        studentResponse.setTotalElements(students.getTotalElements());
        studentResponse.setTotalPages(students.getTotalPages());
        studentResponse.setLast(students.isLast());

        return studentResponse;
    }

    @Override
    public StudentDTO getStudentById(int id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student could not be found"));
        return mapToDto(student);
    }

    @Override
    public StudentDTO updateStudent(StudentDTO studentDTO, int id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Pokemon could not be updated"));

        student.setName(studentDTO.getName());
        student.setAge(studentDTO.getAge());

        Student updatedStudent = studentRepository.save(student);
        return mapToDto(updatedStudent);
    }

    @Override
    public void deleteStudent(int id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Pokemon could not be delete"));
        studentRepository.delete(student);
    }

    private StudentDTO mapToDto(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId((int) student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setAge(student.getAge());
        return studentDTO;
    }

    private Student mapToTeacher(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setAge(studentDTO.getAge());
        return student;
    }
}