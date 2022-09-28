package com.example.bedms.controller;

import com.example.bedms.model.Category;
import com.example.bedms.model.Device;
import com.example.bedms.model.Floor;
import com.example.bedms.model.Room;
import com.example.bedms.model.dto.FloorDTO;
import com.example.bedms.model.dto.RoomDTO;
import com.example.bedms.service.FloorService;
import com.example.bedms.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/floors")
@CrossOrigin
public class FloorController {
    @Autowired
    private FloorService floorService;
    @Autowired
    private GeneralService generalService;

    @GetMapping("")
    public ResponseEntity<List<Floor>> findAll() {
        return new ResponseEntity<>(floorService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Floor> findById(@PathVariable int id) {
        return new ResponseEntity<>(floorService.findById(id), HttpStatus.OK);
    }

    @GetMapping("dto/{id}")
    public ResponseEntity<FloorDTO> findDTOById(@PathVariable int id) {
        Floor floor= floorService.findById(id);
        FloorDTO floorDTO = new FloorDTO();
        floorDTO.setId(floor.getId());
        floorDTO.setName(floor.getName());
        floorDTO.setCampus(floor.getCampus());
        floorDTO.setDeviceQuantity(floor.getDeviceList().size());
        floorDTO.setRoomQuantity(floor.getRoomList().size());

        List<String> statisticDeviceList = generalService.statistic(floor.getDeviceList());
        floorDTO.setStatisticDeviceList(statisticDeviceList);

        return new ResponseEntity<>(floorDTO, HttpStatus.OK);
    }

    @GetMapping("campus/name/{name}")
    public ResponseEntity<List<Floor>> findFloorByCampus_Name(@PathVariable String name) {
        return new ResponseEntity<>(floorService.findFloorByCampus_Name(name), HttpStatus.OK);
    }

    //    Get roomDTO by floorId
    @GetMapping("/rooms/{floorId}")
    public ResponseEntity<List<RoomDTO>> findRoomDTOByFloorId(@PathVariable int floorId) {
        Floor floor = floorService.findById(floorId);

        List<RoomDTO> roomDTOList = new ArrayList<>();
        for (int i = 0; i < floor.getRoomList().size(); i++) {
            RoomDTO roomDTO = new RoomDTO();
            roomDTO.setId(floor.getRoomList().get(i).getId());
            roomDTO.setName(floor.getRoomList().get(i).getName());
            roomDTO.setDeviceQuantity(floor.getRoomList().get(i).getDeviceList().size());
            roomDTO.setDeviceList(floor.getRoomList().get(i).getDeviceList());

            List<String> statisticDeviceList = generalService.statistic(floor.getRoomList().get(i).getDeviceList());
            roomDTO.setStatisticDeviceList(statisticDeviceList);
            roomDTOList.add(roomDTO);
        }

        Collections.sort(roomDTOList, (o1, o2) -> o1.getName().compareTo(o2.getName()));
        return new ResponseEntity<>(roomDTOList, HttpStatus.OK);
    }

    @GetMapping("devices/{floorId}")
    public ResponseEntity<List<Device>> findDeviceByFloorId(@PathVariable int floorId) {
        Floor floor = floorService.findById(floorId);
        Collections.sort(floor.getDeviceList(), Comparator.comparingInt(o -> o.getRoom().getId()));
        return new ResponseEntity<>(floor.getDeviceList(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<String> save(@RequestBody Floor floor) {
        floorService.save(floor);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
