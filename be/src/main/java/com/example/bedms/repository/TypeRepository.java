package com.example.bedms.repository;

import com.example.bedms.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeRepository extends JpaRepository<Type,Integer> {
    List<Type> findAllByOrderByName();
}
