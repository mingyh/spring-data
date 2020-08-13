package cn.ming.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2020/8/13.
 */
@RestController
public class JDBCController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //查询数据库的所有信息
    //没有实体类，对数据库的数据获取 Map
    //http://localhost:8080/userList
    @GetMapping("/userList")
    public List<Map<String,Object>> userList(){
        String sql = "select * from user";
        List<Map<String, Object>> list_maps = jdbcTemplate.queryForList(sql);
        return list_maps;
    }

    //新增用户
    //http://localhost:8080/addUser
    @GetMapping("/addUser")
    public String addUser(){
        String sql = "insert into mybatis.user(id,name,pwd) values (5,'小明','123654')";
        jdbcTemplate.update(sql);
        return "update-ok";
    }

    //修改用户
    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") int id){
        String sql = "update mybatis.user set name=?,pwd=? where id="+ id;
        //封装
        Object[] objects = new Object[2];
        objects[0] = "单子";
        objects[1] = "danzi";
        jdbcTemplate.update(sql,objects);
        return "updateUser-ok";
    }

    //删除用户
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id")int id){
        String sql = "delete from mybatis.user where id = ?";
        jdbcTemplate.update(sql,id);
        return "deleteUser-ok";
    }
}
