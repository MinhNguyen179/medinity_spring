package minh_demo.demo.repository;

import minh_demo.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByName(String username);
}

