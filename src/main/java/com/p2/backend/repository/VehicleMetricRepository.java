package com.p2.backend.repository;

import com.p2.backend.entity.Vehicle;
import com.p2.backend.entity.VehicleMetric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleMetricRepository extends JpaRepository<VehicleMetric, Long> {

  @Query("SELECT vm FROM VehicleMetric vm WHERE vm.vehicle.id = :vehicleId ORDER BY vm.timestamp DESC")
  List<VehicleMetric> findLatestMetricsByVehicleId(Long vehicleId);

  Optional<VehicleMetric> findTopByVehicleOrderByTimestampDesc(Vehicle vehicle);

}
