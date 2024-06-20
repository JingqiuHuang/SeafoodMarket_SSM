package com.example.seafood.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.seafood.pojo.SeaFood;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SeaFoodMapper extends BaseMapper<SeaFood> {

    List<SeaFood> selectSeafoodForList();

    void insertSeafood(@Param("seafood") SeaFood seafood);

    void updateSeafood(@Param("seafood") SeaFood seafood);

    void deleteSeafood(@Param("id") Integer id);

    SeaFood selectSeafoodById(@Param("id") Integer id);

    List<SeaFood> selectSeafoodForScope(@Param("min") Integer min, @Param("max") Integer max);

    void updateCartItem(@Param("seaFood") SeaFood seaFood);

    void updateOrderItem(@Param("seaFood") SeaFood seaFood);
}
