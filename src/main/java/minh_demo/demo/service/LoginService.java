package minh_demo.demo.service;

import minh_demo.demo.dto.model.AppUserDto;
import minh_demo.demo.dto.request.LoginDTO;

import java.util.UUID;

public interface LoginService {
    LoginDTO save(LoginDTO logDto, AppUserDto currentUser);

//    LoginDTO save(LoginDTO logDto, UUID currentUserId, UUID tenantId);

}
