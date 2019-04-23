package com.example.demo.model;

import lombok.Data;

import java.util.Date;

/**
 * @author: ayh
 * @create: 2019-04-10 17:30
 **/
@Data
public class Goods {
    private int id;
    private String goods;
    private double price;
    private Date createTime;
    private Integer control;
}
