package minh_demo.demo.service;

import minh_demo.demo.dao.StudentDAO;
import minh_demo.demo.model.Student;
import minh_demo.demo.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepository;

    @Autowired
    private StudentDAO studentDAO;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    public Student getById(Integer id) {
        Student user = studentDAO.getById(id);
        return user;
    }
}
