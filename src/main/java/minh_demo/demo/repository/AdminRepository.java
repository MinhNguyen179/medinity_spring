package minh_demo.demo.repository;

import minh_demo.demo.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByUsername(String username);
    Boolean existsByUsername(String username);}
