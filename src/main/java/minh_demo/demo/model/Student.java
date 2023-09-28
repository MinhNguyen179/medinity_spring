package minh_demo.demo.model;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import lombok.Data;
@Data
@Entity
@Table(name = "students")
public class Student {
    @Id
    private long id;
    private String name;
    private int age;
}
