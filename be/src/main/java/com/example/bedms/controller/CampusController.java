package com.example.bedms.controller;

import com.example.bedms.model.*;
import com.example.bedms.model.dto.CampusDTO;
import com.example.bedms.model.dto.FloorDTO;
import com.example.bedms.model.dto.OrgDTO;
import com.example.bedms.service.CampusService;
import com.example.bedms.service.DeviceService;
import com.example.bedms.service.GeneralService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/campus")
@CrossOrigin
public class CampusController {
    @Autowired
    private CampusService campusService;
    @Autowired
    private GeneralService generalService;

    @GetMapping("")
    public ResponseEntity<List<Campus>> findAll() {
        return new ResponseEntity<>(campusService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Campus> findById(@PathVariable int id) {
        return new ResponseEntity<>(campusService.findById(id), HttpStatus.OK);
    }

    @GetMapping("dto/{id}")
    public ResponseEntity<CampusDTO> findDTOById(@PathVariable int id) {
        Campus campus = campusService.findById(id);
        CampusDTO campusDTO = new CampusDTO();
        campusDTO.setId(campus.getId());
        campusDTO.setName(campus.getName());
        campusDTO.setFloorQuantity(campus.getFloorList().size());
        int roomQuantity = 0;
        for (int i = 0; i < campus.getFloorList().size(); i++) {
            roomQuantity += campus.getFloorList().get(i).getRoomList().size();
        }
        campusDTO.setRoomQuantity(roomQuantity);
        campusDTO.setDeviceQuantity(campus.getDeviceList().size());

        List<String> statisticDeviceList=generalService.statistic(campus.getDeviceList());
        campusDTO.setStatisticDeviceList(statisticDeviceList);
        return new ResponseEntity<>(campusDTO, HttpStatus.OK);
    }

    //    Get all campus DTO
    @GetMapping("dto")
    public ResponseEntity<List<CampusDTO>> findAllDTO() {
        List<Campus> campusList = campusService.findAll();
        List<CampusDTO> campusDTOList = new ArrayList<>();
        for (int i = 0; i < campusList.size(); i++) {
            CampusDTO campusDTO = new CampusDTO();
            campusDTO.setId(campusList.get(i).getId());
            campusDTO.setName(campusList.get(i).getName());
            campusDTO.setFloorQuantity(campusList.get(i).getFloorList().size());
            int roomQuantity = 0;
            for (int j = 0; j < campusList.get(i).getFloorList().size(); j++) {
                roomQuantity += campusList.get(i).getFloorList().get(j).getRoomList().size();
            }
            campusDTO.setRoomQuantity(roomQuantity);
            campusDTO.setDeviceQuantity(campusList.get(i).getDeviceList().size());
            List<String> statisticDeviceList= generalService.statistic(campusList.get(i).getDeviceList());
            campusDTO.setStatisticDeviceList(statisticDeviceList);
            campusDTOList.add(campusDTO);
        }
        return new ResponseEntity<>(campusDTOList, HttpStatus.OK);
    }

    //    Get floorDTO by campustId
    @GetMapping("floors/{campusId}")
    public ResponseEntity<List<FloorDTO>> findFloorDTOByCampusId(@PathVariable int campusId) {
        Campus campus = campusService.findById(campusId);
        List<FloorDTO> floorDTOList = new ArrayList<>();
        for (int i = 0; i < campus.getFloorList().size(); i++) {
            FloorDTO floorDTO = new FloorDTO();
            floorDTO.setId(campus.getFloorList().get(i).getId());
            floorDTO.setName(campus.getFloorList().get(i).getName());
            floorDTO.setRoomQuantity(campus.getFloorList().get(i).getRoomList().size());
            floorDTO.setDeviceQuantity(campus.getFloorList().get(i).getDeviceList().size());
            floorDTO.setDeviceList(campus.getFloorList().get(i).getDeviceList());

            List<String> statisticDeviceList=generalService.statistic(campus.getFloorList().get(i).getDeviceList());
            floorDTO.setStatisticDeviceList(statisticDeviceList);

            floorDTOList.add(floorDTO);
        }
        return new ResponseEntity<>(floorDTOList, HttpStatus.OK);
    }

    //    Get device by campusId
    @GetMapping("devices/{campusId}")
    public ResponseEntity<List<Device>> findDeviceByCampusId(@PathVariable int campusId) {
        Campus campus = campusService.findById(campusId);
        return new ResponseEntity<>(campus.getDeviceList(), HttpStatus.OK);
    }

    @GetMapping("statistic")
    public ResponseEntity<OrgDTO> getStatisticDevice() {
        List<Campus> campusList = campusService.findAll();
        List<Device> deviceList = new ArrayList<>();
        List<Floor> floorList = new ArrayList<>();
        OrgDTO orgDTO = new OrgDTO();
        orgDTO.setCampusQuantity(campusList.size());

        for (int i = 0; i < campusList.size(); i++) {
            deviceList.addAll(campusList.get(i).getDeviceList());
            floorList.addAll(campusList.get(i).getFloorList());
        }

        orgDTO.setFloorQuantity(floorList.size());
        orgDTO.setDeviceQuantity(deviceList.size());
        List<String> statisticDeviceList = generalService.statistic(deviceList);
        orgDTO.setStatisticDeviceList(statisticDeviceList);

//       Get room Quantity
        int roomQuantity = 0;
        for (int l = 0; l < floorList.size(); l++) {
            roomQuantity += floorList.get(l).getRoomList().size();
        }
        orgDTO.setRoomQuantity(roomQuantity);

        return new ResponseEntity<>(orgDTO, HttpStatus.OK);
    }

}
