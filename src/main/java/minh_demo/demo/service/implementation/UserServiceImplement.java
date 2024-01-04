package minh_demo.demo.service.implementation;

import minh_demo.demo.dto.model.UserDTO;
import minh_demo.demo.dto.response.BasicResponse;
import minh_demo.demo.model.User;
import minh_demo.demo.repository.UserRepository;
import minh_demo.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public BasicResponse insertUserInfor(UUID userId, UserDTO userDTO) {
        User userCredential = userRepository.findById(userId).orElse(
                new User()
        );
        BasicResponse basicResponse = new BasicResponse();
        userCredential.setUsername(userDTO.getUsername());

        return basicResponse;
    }
    // Check if user is logged symptoms for himself or for someone else
    // if (myselfLogged) {
    //     userCredential.setUsername(userDTO.getUsername());
    //     userCredential.setGender(userDTO.getGender());
    //     userCredential.setPregnant(userDTO.getPregnant());
    // } else {
    //     userCredential.setUsername(username);
}
