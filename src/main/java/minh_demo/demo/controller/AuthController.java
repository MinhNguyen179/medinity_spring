package minh_demo.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import minh_demo.demo.dto.model.AppUserDto;
import minh_demo.demo.dto.request.LoginDTO;
import minh_demo.demo.dto.request.LoginRequest;
import minh_demo.demo.dto.request.RegisterDTO;
import minh_demo.demo.dto.response.AuthResponseDTO;
import minh_demo.demo.exceptions.InvalidUsernameOrPassword;
import minh_demo.demo.model.Role;
import minh_demo.demo.model.Teacher;
import minh_demo.demo.model.enums.ActionStatus;
import minh_demo.demo.model.enums.ActionType;
import minh_demo.demo.model.enums.EntityType;
import minh_demo.demo.repository.RoleRepository;
import minh_demo.demo.config.JWTGenerator;
import minh_demo.demo.repository.TeacherRepository;
import io.swagger.v3.oas.annotations.Operation;
import minh_demo.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired

    private TeacherRepository teacherRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTGenerator jwtGenerator;
    @Autowired
    LoginService loginService;

//    @Autowired
//    public AuthController(AuthenticationManager authenticationManager, TeacherRepository teacherRepository,
//                          RoleRepository roleRepository, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
//        this.authenticationManager = authenticationManager;
//        this.teacherRepository = teacherRepository;
//        this.roleRepository = roleRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.jwtGenerator = jwtGenerator;
//    }

    @PostMapping("login")
    @Operation(summary = "Login method to get user JWT token data (loginEndpoint)")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequest loginRequest){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtGenerator.generateToken(authentication);
            return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
        } catch (AuthenticationException e) {
            ObjectMapper objectMapper = new ObjectMapper();
            loginService.save(LoginDTO.builder()
                    .entityType(EntityType.USER)
                    .actionData(objectMapper.valueToTree(loginRequest))
                    .actionStatus(ActionStatus.FAILURE)
                    .actionType(ActionType.LOGIN)
                    .actionFailureDetails("Incorrect email or password").build(), new AppUserDto());
            throw new InvalidUsernameOrPassword("Incorrect email or password");
        }
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDto) {
        if (teacherRepository.existsByUsername(registerDto.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        Teacher user = new Teacher();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));

        Role roles = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));

        teacherRepository.save(user);

        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }
}
