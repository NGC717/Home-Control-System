package com.lq.housesystem.controller;

import com.lq.housesystem.mqtt.MqttGateway;
import com.lq.housesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MqttController {

    @Resource
    private MqttGateway mqttGateway;

    @Autowired
    private UserService userService;

    @RequestMapping("/lightControlSend")
    @ResponseBody
    public String lightControlSend(){

        mqttGateway.sendMessage("","31415");

        return "data";
    }

    @RequestMapping("/editUpdatePeriod")
    public String editUpdatePeriod(HttpServletRequest request){
        String period = request.getParameter("period");
        HttpSession session = request.getSession();
        String id = session.getAttribute("editEquipmentId").toString();

        String ip = userService.selectEquipmentIpById(Integer.parseInt(id));

        mqttGateway.sendMessage(ip,period);

        return "edit";
    }

    @RequestMapping("/turnOn")
    public String turnOn(HttpServletRequest request){
        String ip = request.getParameter("ip");

        mqttGateway.sendMessage(ip,"1");

        userService.turnEquipmentOnByIp(ip);

        return "control";
    }

    @RequestMapping("/turnOff")
    public String turnOff(HttpServletRequest request){
        String ip = request.getParameter("ip");

        mqttGateway.sendMessage(ip,"0");

        userService.turnEquipmentOffByIp(ip);

        return "control";
    }
}
