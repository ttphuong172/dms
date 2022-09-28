package com.example.bedms.model.dto;

import com.example.bedms.model.Campus;
import com.example.bedms.model.Device;
import lombok.Data;

import java.util.List;

@Data
public class FloorDTO {
    private int id;
    private String name;
    private Campus campus;
    private int roomQuantity;
    private int deviceQuantity;
    private List<String> statisticDeviceList;
    List<Device> deviceList;
}
