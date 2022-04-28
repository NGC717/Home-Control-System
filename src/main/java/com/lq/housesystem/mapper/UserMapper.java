package com.lq.housesystem.mapper;

import com.lq.housesystem.bean.Equipment;
import com.lq.housesystem.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    void insertUser(User user);

    User selectUser(String userName,String password);

    void insertEquipment(Equipment equipment);

    List<Equipment> selectEquipmentById(Integer uId);

    void deleteEquipment(Integer eId);

    void updateEquipment(Integer eId,Integer state);
}
