package com.lq.housesystem.tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lq.housesystem.bean.Equipment;

public class JsonTools {

    public static String creatJsonObj(Object list) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(list);
    }

    public static String creatJsonObj(Equipment equipment) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(equipment);
    }

    public static String creatJsonObj(String s) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(s);
    }
}
