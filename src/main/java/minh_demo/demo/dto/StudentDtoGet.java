package minh_demo.demo.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import minh_demo.demo.model.Student;

@Data
@NoArgsConstructor
public class StudentDtoGet {
    private Long id;
    private String names;
    private String location;
    private String email;

}
