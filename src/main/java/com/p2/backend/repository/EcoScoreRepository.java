package com.p2.backend.repository;

import com.p2.backend.entity.EcoScore;
import com.p2.backend.entity.Vehicle;
// import com.p2.backend.entity.VehicleMetric;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EcoScoreRepository extends JpaRepository<EcoScore, Long> {
  List<EcoScore> findByVehicle(Vehicle vehicle);

  // Use this version if you're passing a Vehicle object
  Optional<EcoScore> findTopByVehicleOrderByTimestampDesc(Vehicle vehicle);

  // ✅ Add this overload to support passing just vehicleId (Long)
  Optional<EcoScore> findTopByVehicleIdOrderByTimestampDesc(Long vehicleId);
}
