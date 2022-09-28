package com.example.bedms.service;

import com.example.bedms.model.Room;

import java.util.List;

public interface RoomService {
    List<Room> findRoomByFloor_Name (String name);
    Room findById(int id);
    void save(Room room);
}
