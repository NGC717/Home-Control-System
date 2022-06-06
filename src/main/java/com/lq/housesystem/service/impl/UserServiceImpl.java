package com.lq.housesystem.service.impl;

import com.lq.housesystem.bean.Equipment;
import com.lq.housesystem.mapper.UserMapper;
import com.lq.housesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void insertEquipment(Equipment equipment) {
        userMapper.insertEquipment(equipment);
    }

    @Override
    public Equipment selectEquipmentByIp(String eIp) {
        return userMapper.selectEquipmentByIp(eIp);
    }

    @Override
    public void deleteEquipment(Integer eid) {
        userMapper.deleteEquipment(eid);
    }

    @Override
    public void updateEquipmentState(Integer eId,Integer state) {
        userMapper.updateEquipmentState(eId,state);
    }

    @Override
    public List<Equipment> selectAllEquipments() {
        return userMapper.selectAllEquipments();
    }

    @Override
    public List<Equipment> selectEquipmentAdded() {
        return userMapper.selectEquipmentAdded();
    }

    @Override
    public void updateEquipment(Equipment equipment) {
        userMapper.updateEquipment(equipment);
    }

    @Override
    public Equipment selectEquipmentById(Integer id) {
        return userMapper.selectEquipmentById(id);
    }

    @Override
    public String selectEquipmentIpById(Integer id) {
        return userMapper.selectEquipmentIpById(id);
    }

    @Override
    public String selectEquipmentTurnByIp(String ip) {
        return userMapper.selectEquipmentTurnByIp(ip);
    }

    @Override
    public void turnEquipmentOnByIp(String ip) {
        userMapper.turnEquipmentOnByIp(ip);
    }

    @Override
    public void turnEquipmentOffByIp(String ip) {
        userMapper.turnEquipmentOffByIp(ip);
    }

    @Override
    public List<Equipment> selectAllEquipmentsExceptSwitch() {
        return userMapper.selectAllEquipmentsExceptSwitch();
    }
}
