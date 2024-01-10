package minh_demo.demo.service.implementation;

import minh_demo.demo.dto.model.UserDTO;
import minh_demo.demo.dto.response.BasicResponse;
import minh_demo.demo.model.User;
import minh_demo.demo.repository.UserRepository;
import minh_demo.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.BackendId;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImplement implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImplement(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public BasicResponse insertUserInfor(UserDTO userDTO, int userId) {
        User userCredential = userRepository.findById(userId).orElse(new User());
        userCredential.setAge(userDTO.getAge());
        userCredential.setGender(userDTO.getGender());
        userCredential.setUsername(userDTO.getUsername());
        userCredential.setPregnant(userDTO.getPregnant());
        return new BasicResponse("Success", 200, "Insert User Infor Success");
    }

    @Override
    public BasicResponse insertGuestInfor(UserDTO userDTO) {
        User userCredential = new User();
        userCredential.setAge(userDTO.getAge());
        userCredential.setGender(userDTO.getGender());
        userCredential.setUsername(userDTO.getUsername());
        userCredential.setPregnant(userDTO.getPregnant());
        return new BasicResponse("Success", 200, "Insert Guest Infor Success");
    }
}
