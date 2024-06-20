package com.example.seafood.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.seafood.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {

    User selectUserByName(@Param("username") String username);

    void insertUser(@Param("user") User user);

    User selectUserByNameAndPassword(@Param("username") String username, @Param("password") String password);
}
