package minh_demo.demo.dao;

import minh_demo.demo.bean.Permission;

import java.util.List;
import java.util.Map;

public interface PermissionDAO {

    List<Permission> getByMap(Map<String, Object> map);

    Permission getById(Integer id);

    Integer create(Permission permission);

    int update(Permission permission);

    List<Permission> getByUserId(Integer userId);

}
