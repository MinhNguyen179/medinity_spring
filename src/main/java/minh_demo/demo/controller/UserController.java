package minh_demo.demo.controller;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.persistence.Basic;
import minh_demo.demo.dto.model.UserDTO;
import minh_demo.demo.dto.request.LoginRequest;
import minh_demo.demo.dto.request.RegisterDTO;
import minh_demo.demo.dto.response.AuthResponseDTO;
import minh_demo.demo.dto.response.BasicResponse;
import minh_demo.demo.dto.response.ItemResponse;
import minh_demo.demo.model.Role;
import minh_demo.demo.model.User;
import minh_demo.demo.repository.RoleRepository;
import minh_demo.demo.config.JWTGenerator;
import minh_demo.demo.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import minh_demo.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserService userService;

    @Autowired
    public UserController(AuthenticationManager authenticationManager, UserRepository userRepository,
                          RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

    }

    @PostMapping("/log-symptoms")
    public ResponseEntity<String> logUserSymptoms(@RequestBody UserDTO userDTO) {
        userService.insertUserInfor(userDTO, userDTO.getId());
        return new ResponseEntity<>("Success log symptoms", HttpStatus.OK);
    }

    @PostMapping("/log-guest-symptoms")
    public ResponseEntity<String> logGuestSymptoms(@RequestBody UserDTO userDTO) {
        userService.insertGuestInfor(userDTO);
        return new ResponseEntity<>("Success log symptoms", HttpStatus.OK);
    }
}
