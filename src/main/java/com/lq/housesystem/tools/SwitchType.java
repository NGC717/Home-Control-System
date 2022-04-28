package com.lq.housesystem.tools;

public class SwitchType {
    public static String type(Integer t){
        switch (t){
            case 1:
                return "温度传感器";
            case 2:
                return "湿度传感器";
            case 3:
                return "光强传感器";
            case 4:
                return "气体传感器";
            case 5:
                return "开关";
        }
        return null;
    }
}
