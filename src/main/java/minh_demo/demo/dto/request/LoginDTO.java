package minh_demo.demo.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@Data
@NoArgsConstructor
public class LoginDTO {
    @NotEmpty(message = "Email may not be empty")
    private String username;
    @NotEmpty(message = "Password may not be empty")
    private String password;
}
