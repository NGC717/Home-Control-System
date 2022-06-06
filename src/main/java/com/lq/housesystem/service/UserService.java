package com.lq.housesystem.service;

import com.lq.housesystem.bean.Equipment;
import java.util.List;

public interface UserService {

    void insertEquipment(Equipment equipment);

    Equipment selectEquipmentByIp(String eIp);

    void deleteEquipment(Integer eId);

    void updateEquipmentState(Integer eId,Integer state);

    List<Equipment> selectAllEquipments();

    List<Equipment> selectEquipmentAdded();

    void updateEquipment(Equipment equipment);

    Equipment selectEquipmentById(Integer parseInt);

    String selectEquipmentIpById(Integer id);

    String selectEquipmentTurnByIp(String ip);

    void turnEquipmentOnByIp(String ip);

    void turnEquipmentOffByIp(String ip);

    List<Equipment> selectAllEquipmentsExceptSwitch();
}
