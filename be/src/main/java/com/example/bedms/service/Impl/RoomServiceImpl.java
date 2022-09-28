package com.example.bedms.service.Impl;

import com.example.bedms.model.Room;
import com.example.bedms.repository.RoomRepository;
import com.example.bedms.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;
    @Override
    public List<Room> findRoomByFloor_Name(String name) {
        return roomRepository.findRoomByFloor_Name(name);
    }

    @Override
    public Room findById(int id) {
        return roomRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Room room) {
        roomRepository.save(room);
    }
}
