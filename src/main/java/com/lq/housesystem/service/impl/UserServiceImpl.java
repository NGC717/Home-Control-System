package com.lq.housesystem.service.impl;

import com.lq.housesystem.bean.Equipment;
import com.lq.housesystem.bean.User;
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
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public User selectUser(String userName, String password) {
        return userMapper.selectUser(userName,password);
    }

    @Override
    public void insertEquipment(Equipment equipment) {
        userMapper.insertEquipment(equipment);
    }

    @Override
    public List<Equipment> selectEquipmentById(Integer id) {
        return userMapper.selectEquipmentById(id);
    }

    @Override
    public void deleteEquipment(Integer eid) {
        userMapper.deleteEquipment(eid);
    }

    @Override
    public void updateEquipment(Integer eId,Integer state) {
        userMapper.updateEquipment(eId,state);
    }
}
