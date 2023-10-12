package minh_demo.demo.dao;

import minh_demo.demo.bean.User;
import minh_demo.demo.model.Student;

import java.util.List;
import java.util.Map;

public interface StudentDAO {

    List<User> getByMap(Map<String, Object> map);
    List<User> getByRoleId(Map<String, Object> map);
    Student getById(Integer id);
    Integer create(User user);
    int update(User user);
    User getByUserName(String userName);
}
