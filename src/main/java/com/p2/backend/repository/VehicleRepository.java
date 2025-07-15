package com.p2.backend.repository;

import com.p2.backend.entity.Vehicle;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
  List<Vehicle> findByUserId(Long userId);

  void deleteByUser_Id(Long userId);

}
