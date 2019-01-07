package com.springbootredis.controller;

import com.alibaba.fastjson.JSONObject;
import com.springbootredis.model.User;
import com.springbootredis.redis.RedisService;
import com.springbootredis.server.UserService;
import com.springbootredis.util.JwtUtil;
import com.springbootredis.util.NetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: laizc
 * @Date: 2019/1/6 14:48
 * @Description: 登陆
 */
@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    /**
     * 登陆
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, String username, String password) throws Exception {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        User list = userService.findById(user);
        if (list == null){
            throw new Exception("密码错误");
        }
        //传入token参数
        Map<String,Object> map = new HashMap<>();
        String ip = NetUtil.getIpAddress(request);
        map.put("ip",ip);
        map.put("user", JSONObject.toJSONString(list));
        String token = JwtUtil.generateToken(map);
        response.setHeader("Authorization",token);
        redisService.put(token,list.getId(),60*60);
        return "success";
    }

    /**
     * 注册
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/add")
    public String add(String username,String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userService.add(user);
        return null;
    }

    /**
     * 查询所有信息
     * @return
     */
    @GetMapping("/list")
    public String list(){
       List<User> list = userService.find();
       return list.toString();
    }
}
