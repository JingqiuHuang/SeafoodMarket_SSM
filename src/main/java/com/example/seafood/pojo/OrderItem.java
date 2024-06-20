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
@TableName("t_order_item")
public class OrderItem {

    /*CREATE TABLE t_order_item (
	id INT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(100),
	`count` INT,
	price DECIMAL(11, 2),
	`total_money`  DECIMAL(11, 2),
	`order_id`  VARCHAR(50),
	FOREIGN KEY (`order_id`) REFERENCES t_order(`order_id`)
);*/
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer count;
    private BigDecimal price;
    private BigDecimal totalMoney;
    private String orderId;
    private String provider;
    private String kind;
    private Integer seafoodId;
}
