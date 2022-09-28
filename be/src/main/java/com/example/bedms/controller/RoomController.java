package com.example.bedms.controller;

import com.example.bedms.model.Device;
import com.example.bedms.model.Room;
import com.example.bedms.model.dto.RoomDTO;
import com.example.bedms.service.GeneralService;
import com.example.bedms.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/rooms")
@CrossOrigin
public class RoomController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private GeneralService generalService;


    @GetMapping("{id}")
    public ResponseEntity<Room> findById(@PathVariable int id) {
        return new ResponseEntity<>(roomService.findById(id), HttpStatus.OK);
    }

    @GetMapping("dto/{id}")
    public ResponseEntity<RoomDTO> findDTOById(@PathVariable int id) {
        Room room = roomService.findById(id);
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(room.getId());
        roomDTO.setName(room.getName());
        roomDTO.setFloor(room.getFloor());
        roomDTO.setDeviceQuantity(room.getDeviceList().size());

        List<String> statisticDeviceList = generalService.statistic(room.getDeviceList());
        roomDTO.setStatisticDeviceList(statisticDeviceList);

        return new ResponseEntity<>(roomDTO, HttpStatus.OK);
    }

    @GetMapping("floor/name/{name}")
    public ResponseEntity<List<Room>> findRoomByFloor_Name(@PathVariable String name) {
        List<Room> roomList = roomService.findRoomByFloor_Name(name);
        Collections.sort(roomList, (o1, o2) -> o1.getName().compareTo(o2.getName()));
        return new ResponseEntity<>(roomList, HttpStatus.OK);
    }

    @GetMapping("devices/{roomId}")
    public ResponseEntity<List<Device>> findDeviceByRoomId(@PathVariable int roomId) {
        Room room = roomService.findById(roomId);
        return new ResponseEntity<>(room.getDeviceList(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<String> save(@RequestBody Room room) {
        roomService.save(room);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
