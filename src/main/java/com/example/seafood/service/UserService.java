package com.example.seafood.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.seafood.pojo.User;

import java.util.Map;

public interface UserService extends IService<User> {

    User getUserByNameAndPassword(String username, String password);

    User getUserByName(String username);

    void saveUser(User user);

    Page<User> getUsers(Integer pageNum, Integer pageSize);

    void deleteUser(Integer id);

    User getUserById(Integer id);

    void updateUser(User user);

    Boolean authPwd(Map<String, String> requestMap);

    Page<User> listPage(Integer pageNum, Integer pageSize);

    Boolean authAdmin(Map<String, String> requestMap);

}
