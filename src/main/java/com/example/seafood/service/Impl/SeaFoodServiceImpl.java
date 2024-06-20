package com.example.seafood.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.seafood.mappers.SeaFoodMapper;
import com.example.seafood.pojo.SeaFood;
import com.example.seafood.service.SeaFoodService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

@Service
public class SeaFoodServiceImpl extends ServiceImpl<SeaFoodMapper, SeaFood> implements SeaFoodService {
    @Resource
    private SeaFoodMapper seafoodMapper;

    @Override
    public Page<SeaFood> getSeaFoodForPage(Integer pageNum, Integer pageSize) {
        Page<SeaFood> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<SeaFood> queryWrapper = new LambdaQueryWrapper<>();
        Page<SeaFood> seaFoodPage = seafoodMapper.selectPage(page, queryWrapper);
        return seaFoodPage;
    }

    @Override
    public SeaFood getSeaFoodById(Integer id) {
        return seafoodMapper.selectSeafoodById(id);
    }

    @Override
    public void saveSeaFood(SeaFood seafood) {
        seafoodMapper.insertSeafood(seafood);
    }

    @Override
    public void updateSeaFood(SeaFood seafood) {
        seafoodMapper.updateSeafood(seafood);
    }

    @Override
    public void deleteSeaFood(Integer id) {
        seafoodMapper.deleteSeafood(id);
    }

    @Override
    public Page<SeaFood> getSeaFoodForScope(Integer pageNum, Integer pageSize, String min, String max, String searchKind, String searchKey) {
        Page<SeaFood> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<SeaFood> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isEmpty(min) || Integer.parseInt(min) < 0)
            min = "0";
        else
            queryWrapper.orderByAsc(SeaFood::getPrice);
        if (StringUtils.isEmpty(max) || Integer.parseInt(max) >= Integer.MAX_VALUE)
            max = "" + Integer.MAX_VALUE;
        else
            queryWrapper.orderByAsc(SeaFood::getPrice);
        queryWrapper.le(SeaFood::getPrice, max);
        queryWrapper.ge(SeaFood::getPrice, min);
        if (!StringUtils.isEmpty(searchKind)) {
            if ("seafood_name".equals(searchKind))
                queryWrapper.like(SeaFood::getName, searchKey);
            if ("provider".equals(searchKind))
                queryWrapper.like(SeaFood::getProvider, searchKey);
            if ("kind".equals(searchKind))
                queryWrapper.like(SeaFood::getKind, searchKey);
        }
        // 添加查询条件
        Page<SeaFood> seaFoodPage = seafoodMapper.selectPage(page, queryWrapper);
        return seaFoodPage;
    }

    @Override
    public boolean updateDetail(SeaFood seaFood) {
        //更新t_seafood表
        int i = baseMapper.updateById(seaFood);
        //更新t_cart_item、t_order_item表
        baseMapper.updateOrderItem(seaFood);
        baseMapper.updateCartItem(seaFood);
        return i > 0;
    }

}
