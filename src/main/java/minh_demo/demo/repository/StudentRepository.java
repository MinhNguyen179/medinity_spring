package minh_demo.demo.repository;

import minh_demo.demo.model.Student;
import minh_demo.demo.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<StudentEntity> findByUsername(String username);
    Boolean existsByUsername(String username);
}

