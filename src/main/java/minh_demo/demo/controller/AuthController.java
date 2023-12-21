package minh_demo.demo.controller;

import minh_demo.demo.dto.request.LoginRequest;
import minh_demo.demo.dto.request.RegisterDTO;
import minh_demo.demo.dto.response.AuthResponseDTO;
import minh_demo.demo.model.Role;
import minh_demo.demo.model.Admin;
import minh_demo.demo.repository.RoleRepository;
import minh_demo.demo.config.JWTGenerator;
import minh_demo.demo.repository.AdminRepository;
import io.swagger.v3.oas.annotations.Operation;
import minh_demo.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    private AuthenticationManager authenticationManager;
    private AdminRepository teacherRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;
    @Autowired
    public AuthController(AuthenticationManager authenticationManager, AdminRepository teacherRepository, UserRepository userRepository,
                          RoleRepository roleRepository, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.teacherRepository = teacherRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }
/*
*           Will be updated with Swagger in the future!
*           LOGIN API
*           Request Body: LoginRequest
*           {
*               "username": "Nguyen Nhat Minh",
*               "password": "Nguyen Nhat Minh"
*           }
*           Response: ResponseEntity
*           - JWT Token
*           - HTTP Status
*               - 200: OK
*               - 500: Internal Server Error
*               - 403: Unauthorized
*               - 404: Page not found
*
*/
    @PostMapping("login")
    @Operation(summary = "Login method to get user JWT token data (loginEndpoint)")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequest loginRequest){
//        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtGenerator.generateToken(authentication);
            return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
            /*
            *
            *       Working on this later due to the complexity of structure
            *
            */
//        } catch (AuthenticationException e) {
//            ObjectMapper objectMapper = new ObjectMapper();
//            loginService.save(LoginDTO.builder()
//                    .entityType(EntityType.USER)
//                    .actionData(objectMapper.valueToTree(loginRequest))
//                    .actionStatus(ActionStatus.FAILURE)
//                    .actionType(ActionType.LOGIN)
//                    .actionFailureDetails("Incorrect email or password").build(), new AppUserDto());
//            throw new InvalidUsernameOrPassword("Incorrect email or password");
//        }
    }
    /*
     *           Will be updated with Swagger in the future!
     *           REGISTER API
     *           Request Body: RegisterDTO
     *           {
     *               "username": "Nguyen Nhat Minh",
     *               "password": "Nguyen Nhat Minh"
     *           }
     *           Response: ResponseEntity
     *           - Message: User registered success
     *           - HTTP Status
     *               - 200: OK
     *               - 500: Internal Server Error
     *               - 403: Unauthorized
     *               - 404: Page not found
     *
     */
    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDto) {
        if (teacherRepository.existsByUsername(registerDto.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        Admin user = new Admin();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));

        Role roles = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));

        teacherRepository.save(user);

        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }
}
