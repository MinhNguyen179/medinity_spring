package minh_demo.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BasicResponse {
    private String headers;
    private int status;
    private String error;

}
