package com.ccjjltx.controller;

import com.ccjjltx.dao.UserMapper;
import com.ccjjltx.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ccj
 * @version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper dao;

    @RequestMapping("/selectid")
    @ResponseBody
    @Cacheable(cacheNames = "content", key = "#id")
    public String selectid(int id) {
        System.out.println("id===" + id);
        User user = dao.selectByPrimaryKey(id);
        return user.getUsername();
    }

    @RequestMapping("/updateid")
    @ResponseBody
    @CachePut(cacheNames = "content", key = "#id")
    public String updateid(int id, String username) {
        System.out.println("id===" + id);
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        dao.updateByPrimaryKeySelective(user);
        return username;
    }
}
