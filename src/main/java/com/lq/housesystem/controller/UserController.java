package com.lq.housesystem.controller;

import com.lq.housesystem.bean.Equipment;
import com.lq.housesystem.bean.User;
import com.lq.housesystem.service.UserService;
import com.lq.housesystem.tools.SwitchType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(HttpServletRequest request){

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.selectUser(username, password);

        if (user != null){
            request.getSession().setAttribute("currentUser",user);
            return "data";
        }
        return "index";
    }

    @RequestMapping("/register")
    public String register(HttpServletRequest request){

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String tel = request.getParameter("tel");

        userService.insertUser(new User(null,username,password,tel,null));

        return "index";
    }

    @RequestMapping("/addEquipment")
    public String addEquipment(HttpServletRequest request){

        String name = request.getParameter("name");
        String ip = request.getParameter("ip");
        Integer port = Integer.valueOf(request.getParameter("port"));
        String location = request.getParameter("location");
        String remark = request.getParameter("remark");
        String type = SwitchType.type(Integer.valueOf(request.getParameter("type")));

        User currentUser = (User)request.getSession().getAttribute("currentUser");

        Equipment equipment = new Equipment(null,name,ip,port,location,remark,type,currentUser.getId());
        equipment.setState(0);

        userService.insertEquipment(equipment);

        return "add";
    }
}
