package minh_demo.demo.service.implementation;

import minh_demo.demo.dto.model.ItemDTO;
import minh_demo.demo.dto.model.UserDTO;
import minh_demo.demo.model.Item;
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
    public UserDTO insertUserInfor(UUID userId, UserDTO userDTO) {
        User userCredential = userRepository.findById(userId).orElse(
                new User()
        );
        userCredential.setUsername(userDTO.getUsername());

        return userResponse;
    }
}
