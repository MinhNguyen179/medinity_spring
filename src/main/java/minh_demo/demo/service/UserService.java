package minh_demo.demo.service;

import minh_demo.demo.dto.model.UserDTO;
import minh_demo.demo.dto.response.BasicResponse;

import java.util.UUID;

public interface UserService {

    BasicResponse insertUserInfor(UserDTO userDTO, int userId);

    BasicResponse insertGuestInfor(UserDTO userDTO);
}
