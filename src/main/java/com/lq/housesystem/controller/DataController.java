package com.lq.housesystem.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lq.housesystem.bean.*;
import com.lq.housesystem.service.DataService;
import com.lq.housesystem.service.UserService;
import com.lq.housesystem.tools.JsonTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class DataController {

    @Autowired
    private UserService userService;

    @Autowired
    private DataService dataService;

    @RequestMapping("/selectEquipment")
    public String selectEquipment(HttpServletRequest request) throws JsonProcessingException {
        List<Equipment> equipments = userService.selectAllEquipments();
        ObjectMapper mapper = new ObjectMapper();
        String value = mapper.writeValueAsString(equipments);
        return value;
    }

    //搜索用户已添加设备
    @RequestMapping("/selectEquipmentAdded")
    public String selectEquipmentAdded(HttpServletRequest request) throws JsonProcessingException {
        List<Equipment> equipmentList = userService.selectEquipmentAdded();
        return JsonTools.creatJsonObj(equipmentList);
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

        userService.updateEquipmentState(eId,check);

        return null;
    }

    @RequestMapping("/getEditEquipment")
    public String getEditEquipment(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String id = session.getAttribute("editEquipmentId").toString();
        return id;
    }

    @RequestMapping("/getEditInfo")
    public String getEditInfo(HttpServletRequest request) throws JsonProcessingException {
        HttpSession session = request.getSession();
        String id = session.getAttribute("editEquipmentId").toString();

        Equipment equipment = userService.selectEquipmentById(Integer.parseInt(id));

        String e = JsonTools.creatJsonObj(equipment);

        return e;
    }

    @RequestMapping("/getGasData")
    public String getGasVal(HttpServletRequest request) throws JsonProcessingException {

        List<Gas> gasData = dataService.getGasData(10);

        String data = JsonTools.creatJsonObj(gasData);

        return data;
    }

    @RequestMapping("/getTempData")
    public String getTempData(HttpServletRequest request) throws JsonProcessingException {

        List<Temperature> data = dataService.getTempData(10);

        String res = JsonTools.creatJsonObj(data);

        return res;
    }

    @RequestMapping("/getHumidityData")
    public String getHumidityData(HttpServletRequest request) throws JsonProcessingException {

        List<Humidity> data = dataService.getHumidityData(10);

        String res = JsonTools.creatJsonObj(data);

        return res;
    }

    @RequestMapping("/getLightData")
    public String getLightData(HttpServletRequest request) throws JsonProcessingException {

        List<Light> data = dataService.getLightData(10);

        String res = JsonTools.creatJsonObj(data);

        return res;
    }

    @RequestMapping("/deleteLight")
    public String deleteLight(HttpServletRequest request) throws JsonProcessingException {

        String id = request.getParameter("id");

        dataService.deleteLightById(Integer.parseInt(id));

        return null;
    }

    @RequestMapping("/deleteGas")
    public String deleteGas(HttpServletRequest request) throws JsonProcessingException {

        String id = request.getParameter("id");

        dataService.deleteGasById(Integer.parseInt(id));

        return null;
    }

    @RequestMapping("/deleteTemp")
    public String deleteTemp(HttpServletRequest request) throws JsonProcessingException {

        String id = request.getParameter("id");

        dataService.deleteTempById(Integer.parseInt(id));

        return null;
    }

    @RequestMapping("/deleteHumidity")
    public String deleteHumidity(HttpServletRequest request) throws JsonProcessingException {

        String id = request.getParameter("id");

        dataService.deleteHumidityById(Integer.parseInt(id));

        return null;
    }

}
