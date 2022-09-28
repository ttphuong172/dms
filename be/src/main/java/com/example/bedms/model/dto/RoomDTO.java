package com.example.bedms.model.dto;


import com.example.bedms.model.Device;
import com.example.bedms.model.Floor;

import lombok.Data;

import java.util.List;


@Data
public class RoomDTO {
    private int id;
    private String name;
    private Floor floor;
    private int deviceQuantity;
    private List<String> statisticDeviceList;
    private List<Device> deviceList;
}
