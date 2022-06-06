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
            case 6:
                return "温湿度传感器";
        }
        return null;
    }

    public static Integer typeId(String ename){
        switch (ename){
            case "DATA-MQ4":
                return 4;
            case "DATA-LIGHT":
                return 3;
            case "DATA-TH":
                return 6;
            case "DATA-SWITCH":
                return 5;
        }

        return 0;
    }
}
