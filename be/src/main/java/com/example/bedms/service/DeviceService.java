package com.example.bedms.service;

import com.example.bedms.model.Device;

import java.util.List;
import java.util.Optional;

public interface DeviceService {
    List<Device> findAll();
    Device save(Device device);
    Device findById(String id);
    void delete(Device device);
    List<Device> searchDevice (String id,Optional<Integer> idCategory,String brand,Optional<Integer> idCampus,Optional<Integer> idFloor,Optional<Integer> idRoom);
}


