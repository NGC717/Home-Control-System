package com.lq.housesystem.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lq.housesystem.bean.Equipment;
import com.lq.housesystem.service.UserService;
import com.lq.housesystem.tools.JsonTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public String register(HttpServletRequest request){

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String tel = request.getParameter("tel");

        return "xxxxxxxxxx";
    }

    //为用户更新搜索到的设备
    @RequestMapping("/addEquipment")
    @ResponseBody
    public String addEquipment(HttpServletRequest request) throws JsonProcessingException {
        Integer eId = Integer.parseInt(request.getParameter("eId"));
        userService.updateEquipmentState(eId,1);
        return "success";
    }

    @RequestMapping("/editEquipment")
    public String editEquipment(HttpServletRequest request) {

        HttpSession session = request.getSession();

        session.setAttribute("editEquipmentId",request.getParameter("eId"));

        return "edit";
    }

    @RequestMapping("/editEquipmentById")
    public String editEquipmentById(HttpServletRequest request) {

        HttpSession session = request.getSession();

        String eId = session.getAttribute("editEquipmentId").toString();

        String name = request.getParameter("name");
        String location = request.getParameter("location");
        String remark = request.getParameter("remark");

        userService.updateEquipment(new Equipment(Integer.parseInt(eId),name,location,remark));

        return "edit";
    }

    @RequestMapping("/getSwitchState")
    @ResponseBody
    public String getSwitchState(HttpServletRequest request) throws JsonProcessingException {
        String ip = request.getParameter("ip");

        String turn = userService.selectEquipmentTurnByIp(ip);

        return turn;
    }

    @RequestMapping("/getAllEqExceptSwitch")
    @ResponseBody
    public String getAllEqExceptSwitch(HttpServletRequest request) throws JsonProcessingException {
        List<Equipment> equipmentList = userService.selectAllEquipmentsExceptSwitch();

        String res = JsonTools.creatJsonObj(equipmentList);

        return res;
    }
}
