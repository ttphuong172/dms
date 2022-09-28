package com.example.bedms.service.Impl;

import com.example.bedms.model.Device;
import com.example.bedms.repository.DeviceRepository;
import com.example.bedms.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;
    @Override
    public List<Device> findAll() {
        return deviceRepository.findAll();
    }

    @Override
    public Device save(Device device) {
        return deviceRepository.save(device);
    }

    @Override
    public Device findById(String id) {
        return deviceRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Device device) {
        deviceRepository.delete(device);
    }

    @Override
    public List<Device> searchDevice(String id,Optional<Integer> idCategory,String brand,Optional<Integer> idCampus,Optional<Integer> idFloor,Optional<Integer> idRoom) {
        return deviceRepository.searchDevice(id,idCategory,brand,idCampus,idFloor,idRoom);
    }


}
