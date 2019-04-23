package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.constant.GoodsConstant;
import com.example.demo.constant.LoginConstant;
import com.example.demo.model.Goods;
import com.example.demo.model.Qg;
import com.example.demo.model.User;
import com.example.demo.service.GoodsService;
import com.example.demo.service.QgService;
import com.example.demo.service.UserService;
import com.example.demo.util.CreateTokenUtil;
import com.example.demo.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

/**
 * @author: ayh
 * @create: 2019-04-10 17:24
 * 测试分布式锁
 **/
@RestController()
@RequestMapping("/api")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private QgService qgService;

    @Autowired
    RedisUtil redisUtil;

    /**
     * 分布式锁
     *
     * @return
     */
    @GetMapping("/qg")
    public String testLock(String token, int goodsId) throws InterruptedException {
        // 1. 获得用户token
        String userJson = (String)redisUtil.get(token);
        User user = JSONObject.parseObject(userJson, User.class);
        // 2.添加分布式锁
        while (!redisUtil.lock(GoodsConstant.GOODS_QG + goodsId, 60L)) {
            Thread.sleep(3);
        }
        // 3.判断该用户是否已经抢购过该商品,若已抢购过则释放锁，返回
        int count = qgService.queryQgCount(user.getId());
        if (count > 0) {
            redisUtil.unLock(GoodsConstant.GOODS_QG + goodsId);
            return "您已抢购过该商品";
        }
        // 4.获得redis抢购商品信息
        Goods goodJson = JSON.parseObject((String) redisUtil.get(GoodsConstant.GOODS + goodsId), Goods.class);
        // 判断商品数量
        if (goodJson.getControl() <= 0) {
            redisUtil.unLock(GoodsConstant.GOODS_QG + goodsId);
            return "商品被抢购完了！";
        }
        // 5.抢购成功，库存-1,刷新redis抢购商品信息
        goodJson.setControl(goodJson.getControl() - 1);
        redisUtil.set(GoodsConstant.GOODS + goodJson.getId(), JSON.toJSONString(goodJson));
        // 6.添加到抢购表
        Qg qg = new Qg();
        qg.setUserId(user.getId());
        qg.setGoodsId(goodJson.getId());
        qg.setStatus(0);
        qgService.saveQg(qg);
        // 7.解锁
        redisUtil.unLock(GoodsConstant.GOODS_QG + goodsId);
        return "success";
    }

    /**
     * 同步mysql数据到redis
     *
     * @return
     */
    @GetMapping("/saveGoodsToRedis")
    public String saveGoodsToRedis() {
        Goods goods = goodsService.goodsInfo();
        redisUtil.set(GoodsConstant.GOODS + goods.getId(), JSON.toJSONString(goods));
        return "success";
    }

    /**
     * 模拟添加用户
     * @return
     */
        @GetMapping("addUser")
    public String addUser() {
        // 获得第一个用户登陆信息
        String tokenByFirstUser = (String) redisUtil.get("1");
        String userStr = (String) redisUtil.get(tokenByFirstUser);
        User user = JSONObject.parseObject(userStr, User.class);
        String token = LoginConstant.TOKEN_PRE;
        // 模拟添加500
        for (int i = 2; i < 501; i++) {
            user.setId(i);
            redisUtil.set(i + "", token + i);
            redisUtil.set(token + i,JSON.toJSONString(user));
        }
        return "success";
    }


}
