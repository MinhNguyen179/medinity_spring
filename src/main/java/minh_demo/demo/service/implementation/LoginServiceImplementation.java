//package minh_demo.demo.service.implementation;

//import minh_demo.demo.dto.model.AppUserDto;
//import minh_demo.demo.dto.request.LoginDTO;
//import minh_demo.demo.model.Log;
//import minh_demo.demo.service.LoginService;
//import org.springframework.beans.BeanUtils;
//
//import java.util.UUID;

//public class LoginServiceImplementation  implements LoginService {

//    @Override
//    public LoginDTO save(LoginDTO logDto, AppUserDto currentUser) {
//        Log log = new Log();
//        BeanUtils.copyProperties(logDto, log, "createdAt", "createdBy", "actionData");
//        log.setCreatedBy(currentUser.getId());
//        log.setTenantId(currentUser.getTenantId());
//        log.setActionData(logDto.getActionData().toString());
//        Log savedLog = logRepository.save(log);
//        return logMapper.toDto(savedLog);
//    }

//    @Override
//    public LoginDTO save(LoginDTO logDto, UUID currentUserId, UUID tenantId) {
//        return null;
//    }
//}
