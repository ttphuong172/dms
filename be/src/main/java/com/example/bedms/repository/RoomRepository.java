package com.example.bedms.repository;

import com.example.bedms.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Integer> {
    List<Room> findRoomByFloor_Name(String name);
}
