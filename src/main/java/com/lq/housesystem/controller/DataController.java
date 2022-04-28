package com.lq.housesystem.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lq.housesystem.bean.Equipment;
import com.lq.housesystem.bean.User;
import com.lq.housesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DataController {

    @Autowired
    private UserService userService;

    @RequestMapping("/selectEquipment")
    public String selectEquipment(HttpServletRequest request) throws JsonProcessingException {
        User currentUser = (User)request.getSession().getAttribute("currentUser");

        List<Equipment> equipments = userService.selectEquipmentById(currentUser.getId());

        ObjectMapper mapper = new ObjectMapper();

        String value = mapper.writeValueAsString(equipments);

        return value;
    }

    @RequestMapping("/deleteEquipment")
    public String   deleteEquipment(HttpServletRequest request) {
        Integer id = Integer.valueOf(request.getParameter("eId"));
        userService.deleteEquipment(id);
        return null;
    }

    @RequestMapping("/updateEquipment")
    public String updateEquipment(HttpServletRequest request) {

        Integer check = Integer.valueOf(request.getParameter("check"));
        Integer eId = Integer.valueOf(request.getParameter("eId"));

        userService.updateEquipment(eId,check);

        return null;
    }

    @RequestMapping("/insertData")
    public String insertData(HttpServletRequest request) throws JsonProcessingException {

        Map<String,String> temp = new HashMap<>();
        Map<String,String> hum = new HashMap<>();

        temp.put("type","1");
        temp.put("data","25.12");
        temp.put("mac","DS54F5SD4FSD");

        hum.put("type","2");
        hum.put("data","34.6");
        hum.put("mac","S5DSA4D4AS6SAD");

        ObjectMapper mapper = new ObjectMapper();

        String tempData = mapper.writeValueAsString(temp);
        String humData = mapper.writeValueAsString(hum);

        return null;
    }
}
