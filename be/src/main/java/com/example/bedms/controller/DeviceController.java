package com.example.bedms.controller;

import com.example.bedms.model.Device;
import com.example.bedms.model.Mantain;
import com.example.bedms.model.dto.DeviceDTO;
import com.example.bedms.model.dto.MantainRequestDTO;
import com.example.bedms.model.dto.StatisticDeviceDTO;
import com.example.bedms.service.DeviceService;
import com.example.bedms.service.GeneralService;
import com.example.bedms.service.MantainService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/devices")
@CrossOrigin
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @Autowired
    private MantainService mantainService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private GeneralService generalService;

    @GetMapping("")
    public ResponseEntity<List<Device>> findAll() {
        List<Device> deviceList = deviceService.findAll();
        Collections.sort(deviceList, Comparator.comparingInt(o -> o.getRoom().getId()));
        return new ResponseEntity<>(deviceList, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<DeviceDTO> findById(@PathVariable String id) {
        Device device = deviceService.findById(id);
        if (device == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            DeviceDTO deviceDTO = modelMapper.map(device, DeviceDTO.class);
            Collections.sort(device.getMantainList(),(o1,o2)->o2.getMantainDate().compareTo(o1.getMantainDate()));
//            Collections.sort(roomList, (o1, o2) -> o1.getName().compareTo(o2.getName()));
            return new ResponseEntity<>(deviceDTO, HttpStatus.OK);
        }
    }

    @PostMapping("")
    public ResponseEntity<Device> save(@RequestBody Device device) {
        Device deviceCurrent = deviceService.findById(device.getId());
        if (deviceCurrent != null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
//        Chỗ này chưa hay
        device.setStatus("Good");

        deviceService.save(device);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());

        return new ResponseEntity<>(device, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> update(@PathVariable String id, @RequestBody Device device) {
        Device deviceCurrent = deviceService.findById(id);
        if (deviceCurrent == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        deviceCurrent.setCategory(device.getCategory());
        deviceCurrent.setUseDate(device.getUseDate());
        deviceCurrent.setBrand(device.getBrand());
        deviceCurrent.setModel(device.getModel());
        deviceCurrent.setSerial(device.getSerial());
        deviceCurrent.setNote(device.getNote());
        deviceCurrent.setStatus(device.getStatus());
        deviceCurrent.setConfigurationList(device.getConfigurationList());
        deviceCurrent.setCampus(device.getCampus());
        deviceCurrent.setFloor(device.getFloor());
        deviceCurrent.setRoom(device.getRoom());
        deviceService.save(deviceCurrent);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        Device device = deviceService.findById(id);
        deviceService.delete(device);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("mantain/{id}")
    public ResponseEntity<String> mantain(@PathVariable String id, @RequestBody MantainRequestDTO mantainRequestDTO) {
        Device device = deviceService.findById(id);

        Mantain mantain = new Mantain();
        mantain.setMantainDate(mantainRequestDTO.getMantainDate());
        mantain.setMantainContent(mantainRequestDTO.getContentMantain());
        mantain.setPersonName(mantainRequestDTO.getPersonName());
        mantain.setMantainDevice(device);

        mantainService.save(mantain);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("search")
    public ResponseEntity<StatisticDeviceDTO> searchDevice(@RequestParam(required = false) String id,@RequestParam Optional<Integer> idCategory,@RequestParam(required = false) String brand,Optional<Integer> idCampus,Optional<Integer> idFloor,Optional<Integer> idRoom) {
        List<Device> deviceList = deviceService.searchDevice(id,idCategory,brand,idCampus,idFloor,idRoom);
        StatisticDeviceDTO statisticDeviceDTO = new StatisticDeviceDTO();
        statisticDeviceDTO.setDeviceList(deviceList);
        List<String> statisticDeviceList= generalService.statistic(deviceList);
        statisticDeviceDTO.setStatisticDeviceList(statisticDeviceList);
        return new ResponseEntity<>(statisticDeviceDTO, HttpStatus.OK);

    }
    @GetMapping("statistic")
    public ResponseEntity<StatisticDeviceDTO> statisticDevice(){
        List<Device> deviceList = deviceService.findAll();
        StatisticDeviceDTO statisticDeviceDTO= new StatisticDeviceDTO();
        statisticDeviceDTO.setDeviceList(deviceList);
        List<String> statisticDeviceList = generalService.statistic(deviceList);
        statisticDeviceDTO.setStatisticDeviceList(statisticDeviceList);
        return new ResponseEntity<>(statisticDeviceDTO,HttpStatus.OK);
    }
}
