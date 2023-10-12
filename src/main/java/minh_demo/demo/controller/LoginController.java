package minh_demo.demo.controller;

import minh_demo.demo.bean.User;
import minh_demo.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class LoginController {
    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/login")
    @ResponseBody
    public Object login(@AuthenticationPrincipal User loginedUser, @RequestParam(name = "logout", required = false) String logout) {
        if (logout != null) {
            return null;
        }
        if (loginedUser != null) {
            return studentService.getById(loginedUser.getId());
        }
        return null;
    }
}
