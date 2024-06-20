package com.example.seafood.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_seafood")
public class SeaFood {

    /*id, NAME, author, price, sales, stock, img_path*/
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private String provider;
    private BigDecimal price;
    private String kind;
    private Integer sales;
    private Integer stock;
    private String imgPath;

}
