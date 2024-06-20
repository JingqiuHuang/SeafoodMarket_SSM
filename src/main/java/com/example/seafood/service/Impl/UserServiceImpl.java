package com.example.seafood.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.seafood.mappers.UserMapper;
import com.example.seafood.pojo.User;
import com.example.seafood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUserByName(String username) {
        return userMapper.selectUserByName(username);
    }

    public void saveUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public Page<User> getUsers(Integer pageNum, Integer pageSize) {
        Page<User> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        Page<User> userPage = userMapper.selectPage(page, queryWrapper);
        return userPage;
    }

    @Override
    public void deleteUser(Integer id) {
        int i = userMapper.deleteById(id);
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectById(id);
    }

    public User getUserByNameAndPassword(String username, String password) {
        return userMapper.selectUserByNameAndPassword(username, password);
    }

    @Override
    public void updateUser(User user) {
        this.updateById(user);
    }

    //用户名和密码验证
    @Override
    public Boolean authPwd(Map<String, String> requestMap) {
        Integer userId = Integer.parseInt(requestMap.get("userId"));
        String password = requestMap.get("password");
        User user = getUserById(userId);
        return user != null && password.equals(user.getPassword());
    }

    //管理员+用户名密码验证
    @Override
    public Boolean authAdmin(Map<String, String> requestMap) {
        Integer userId = Integer.parseInt(requestMap.get("userId"));
        String password = requestMap.get("password");
        User user = getUserById(userId);
        return user != null && password.equals(user.getPassword()) && user.getRole() == 0;
    }

    @Override
    public Page<User> listPage(Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        Page<User> page = new Page<>(pageNum, pageSize);
        return this.baseMapper.selectPage(page, queryWrapper);
    }
}
