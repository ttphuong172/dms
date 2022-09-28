package com.example.bedms.repository;

import com.example.bedms.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, String> {
    @Query("SELECT d FROM Device d WHERE (d.id like %:id%) and (:idCategory is null or d.category.id=:idCategory) and (d.brand like %:brand%)  and (:idCampus is null or d.campus.id=:idCampus) and (:idFloor is null or d.floor.id=:idFloor) and (:idRoom is null or d.room.id=:idRoom) ORDER BY d.id")
    List<Device> searchDevice(@Param("id") String id, @Param("idCategory") Optional<Integer> idCategory,@Param("brand") String brand,@Param("idCampus") Optional<Integer> idCampus,@Param("idFloor") Optional<Integer> idFloor,@Param("idRoom") Optional<Integer> idRoom);
}
