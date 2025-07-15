package com.p2.backend.repository;

import com.p2.backend.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {
  List<Alert> findByVehicleId(Long vehicleId);
}
