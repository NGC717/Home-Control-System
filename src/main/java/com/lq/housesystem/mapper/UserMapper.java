package com.lq.housesystem.mapper;

import com.lq.housesystem.bean.Equipment;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserMapper {
    void insertEquipment(Equipment equipment);

    Equipment selectEquipmentByIp(String eIp);

    void deleteEquipment(Integer eId);

    void updateEquipmentState(Integer eId,Integer state);

    List<Equipment> selectAllEquipments();

    List<Equipment> selectEquipmentAdded();

    void updateEquipment(Equipment equipment);

    Equipment selectEquipmentById(Integer id);

    String selectEquipmentIpById(Integer id);

    String selectEquipmentTurnByIp(String ip);

    void turnEquipmentOnByIp(String ip);

    void turnEquipmentOffByIp(String ip);

    List<Equipment> selectAllEquipmentsExceptSwitch();
}
