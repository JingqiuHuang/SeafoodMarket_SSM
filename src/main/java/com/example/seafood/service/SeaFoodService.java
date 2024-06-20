package com.example.seafood.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.seafood.pojo.SeaFood;

public interface SeaFoodService extends IService<SeaFood> {

    Page<SeaFood> getSeaFoodForPage(Integer pageNum, Integer pageSize);

    SeaFood getSeaFoodById(Integer id);

    void saveSeaFood(SeaFood seafood);

    void updateSeaFood(SeaFood seafood);

    void deleteSeaFood(Integer id);

    Page<SeaFood> getSeaFoodForScope(Integer pageNum, Integer pageSize, String min, String max, String searchKind, String searchKey);

    boolean updateDetail(SeaFood seaFood);

}
