package com.example.seafood.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.seafood.common.ResponseData;
import com.example.seafood.pojo.User;
import com.example.seafood.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("user")
@RestController
public class UserController {

    @Resource
    private UserService userService;

    //登录
    @PostMapping("login")
    public ResponseData login(@RequestBody User user) {
        User res = userService.getUserByNameAndPassword(user.getUsername(), user.getPassword());
        if (res != null) {
            return ResponseData.ok(res.getId());
        } else {
            return ResponseData.fail().message("用户名或密码错误");
        }
    }

    //鉴权api
    @GetMapping("auth/{userId}")
    public ResponseData auth(@PathVariable String userId) {
        User user = userService.getUserById(Integer.valueOf(userId));
        if (user.getRole() == 0)
            return ResponseData.ok();
        else
            return ResponseData.fail();
    }

    //管理员查询全部用户
    @GetMapping("list")
    public ResponseData list(@RequestParam Map<String, String> map) {
        if (userService.authAdmin(map)) {
            Page<User> userPage = userService.listPage(Integer.parseInt(map.get("pageNum")), Integer.parseInt(map.get("pageSize")));
            return ResponseData.ok(userPage);
        } else
            return ResponseData.fail();
    }

    //删除用户
    @GetMapping("delete/{id}")
    public ResponseData delete(@PathVariable String id, @RequestParam Map<String, String> map) {
        if (userService.authAdmin(map)) {
            boolean b = userService.removeById(id);
            if (b)
                return ResponseData.ok();
            else
                return ResponseData.fail();
        } else
            return ResponseData.fail();
    }

    //根据用户id查询用户
    @GetMapping("get/{id}")
    public ResponseData get(@PathVariable String id) {
        User userById = userService.getUserById(Integer.valueOf(id));
        return ResponseData.ok(userById);
    }

    //更新用户信息
    @PostMapping("update")
    public ResponseData update(@RequestBody User user, @RequestParam Map<String, String> map) {
        if (userService.authPwd(map)) {
            userService.updateUser(user);
            return ResponseData.ok();
        } else
            return ResponseData.fail();
    }

    //用户添加
    @PostMapping("add")
    public ResponseData add(@RequestBody User user, @RequestParam Map<String, String> map) {
        if (userService.authAdmin(map)) {
            userService.saveUser(user);
            return ResponseData.ok();
        } else
            return ResponseData.fail();
    }

    //注册
    @PostMapping("register")
    public ResponseData register(@RequestBody User user) {
        if (userService.getUserByName(user.getUsername()) == null) {
            user.setRole(1);
            userService.saveUser(user);
            return ResponseData.ok();
        } else
            return ResponseData.fail().message("用户名重复");
    }
}
