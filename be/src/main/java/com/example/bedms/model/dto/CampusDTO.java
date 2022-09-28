package com.example.bedms.model.dto;

import com.example.bedms.model.Device;
import lombok.Data;

import java.util.List;
@Data
public class CampusDTO {
    private int id;
    private String name;
    private int floorQuantity;
    private int roomQuantity;
    private int deviceQuantity;
//    List<Device> deviceList;
    private List<String> statisticDeviceList;
}
