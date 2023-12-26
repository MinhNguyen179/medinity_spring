package minh_demo.demo.dto.request;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import minh_demo.demo.dto.model.AppUserDto;
import minh_demo.demo.model.enums.ActionStatus;
import minh_demo.demo.model.enums.ActionType;
import minh_demo.demo.model.enums.EntityType;


import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDTO {
    private UUID id;
    private UUID tenantId;
    private UUID entityId;
    private EntityType entityType;
    private AppUserDto createdBy;
    private JsonNode actionData;
    private ActionStatus actionStatus;
    private ActionType actionType;
    private String actionFailureDetails;
    private Date createdAt;
}
