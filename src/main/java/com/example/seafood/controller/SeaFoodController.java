package com.example.seafood.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.seafood.common.ResponseData;
import com.example.seafood.pojo.SeaFood;
import com.example.seafood.service.SeaFoodService;
import com.example.seafood.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("seafood")
@RestController
public class SeaFoodController {

    @Resource
    UserService userService;
    @Resource
    private SeaFoodService seafoodService;

    //分页、按种类查询
    @GetMapping("list")
    public ResponseData list(@RequestParam Map<Object, Object> pageQuery) {
        Page<SeaFood> seaFoodForScope = seafoodService.getSeaFoodForScope(Integer.parseInt(pageQuery.get("pageNum").toString()), Integer.parseInt(pageQuery.get("pageSize").toString()), (String) pageQuery.get("minPrice"), (String) pageQuery.get("maxPrice"), (String) pageQuery.get("queryKind"), (String) pageQuery.get("queryKey"));
        return ResponseData.ok(seaFoodForScope);
    }

    @GetMapping("get/{id}")
    public ResponseData getById(@PathVariable String id) {
        SeaFood seaFood = seafoodService.getById(id);
        return ResponseData.ok(seaFood);
    }

    @PostMapping("update")
    public ResponseData update(@RequestBody SeaFood seaFood) {
        boolean b = seafoodService.updateDetail(seaFood);
        if (b)
            return ResponseData.ok();
        else
            return ResponseData.fail();
    }

    @GetMapping("delete/{id}")
    public ResponseData delete(@PathVariable String id, @RequestParam Map<String, String> requestMap) {
        if (userService.authPwd(requestMap)) {
            seafoodService.deleteSeaFood(Integer.valueOf(id));
            return ResponseData.ok();
        }
        return ResponseData.fail();

    }

    @PostMapping("add")
    public ResponseData add(@RequestBody SeaFood seaFood) {
        seafoodService.saveSeaFood(seaFood);
        return ResponseData.ok();
    }

}
