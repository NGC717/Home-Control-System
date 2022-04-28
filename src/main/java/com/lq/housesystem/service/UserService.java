package com.lq.housesystem.service;

import com.lq.housesystem.bean.Equipment;
import com.lq.housesystem.bean.User;

import java.util.List;

public interface UserService {

    void insertUser(User user);

    User selectUser(String userName,String password);

    void insertEquipment(Equipment equipment);

    List<Equipment> selectEquipmentById(Integer uId);

    void deleteEquipment(Integer eId);

    void updateEquipment(Integer eId,Integer state);
}
