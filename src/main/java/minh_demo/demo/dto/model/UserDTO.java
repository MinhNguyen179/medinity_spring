package minh_demo.demo.dto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private UUID id;
    private String username;
    private int age;
    private String gender;
    private Boolean isPregnant;
    private String extraData;

}