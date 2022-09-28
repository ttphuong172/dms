package com.example.bedms.repository;

import com.example.bedms.model.Floor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FloorRepository extends JpaRepository<Floor,Integer> {
    List<Floor> findFloorByCampus_Name(String name);
}
