package com.p2.backend.service;

import com.p2.backend.dto.VehicleComparisonDTO;
import com.p2.backend.entity.Vehicle;
import com.p2.backend.entity.VehicleMetric;
import com.p2.backend.entity.EcoScore;
import com.p2.backend.repository.VehicleRepository;
import com.p2.backend.repository.VehicleMetricRepository;
import com.p2.backend.repository.EcoScoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComparisonService {

  private final VehicleRepository vehicleRepo;
  private final VehicleMetricRepository metricRepo;
  private final EcoScoreRepository ecoScoreRepo;

  public ComparisonService(VehicleRepository vehicleRepo, VehicleMetricRepository metricRepo,
      EcoScoreRepository ecoScoreRepo) {
    this.vehicleRepo = vehicleRepo;
    this.metricRepo = metricRepo;
    this.ecoScoreRepo = ecoScoreRepo;
  }

  public VehicleComparisonDTO compareVehicles(Long vid1, Long vid2) {
    Vehicle v1 = vehicleRepo.findById(vid1).orElseThrow();
    Vehicle v2 = vehicleRepo.findById(vid2).orElseThrow();

    List<VehicleMetric> metrics1 = metricRepo.findLatestMetricsByVehicleId(vid1);
    List<VehicleMetric> metrics2 = metricRepo.findLatestMetricsByVehicleId(vid2);

    double avgFuel1 = metrics1.stream().mapToDouble(VehicleMetric::getFuelUsed).average().orElse(0);
    double avgFuel2 = metrics2.stream().mapToDouble(VehicleMetric::getFuelUsed).average().orElse(0);

    double avgEmis1 = metrics1.stream().mapToDouble(VehicleMetric::getEmissions).average().orElse(0);
    double avgEmis2 = metrics2.stream().mapToDouble(VehicleMetric::getEmissions).average().orElse(0);

    // double eco1 =
    // ecoScoreRepo.findTopByVehicleOrderByTimestampDesc(vid1).map(EcoScore::getScore).orElse(0.0);
    // double eco2 =
    // ecoScoreRepo.findTopByVehicleOrderByTimestampDesc(vid2).map(EcoScore::getScore).orElse(0.0);
    double eco1 = ecoScoreRepo.findTopByVehicleIdOrderByTimestampDesc(vid1).map(EcoScore::getScore).orElse(0.0);
    double eco2 = ecoScoreRepo.findTopByVehicleIdOrderByTimestampDesc(vid2).map(EcoScore::getScore).orElse(0.0);

    return new VehicleComparisonDTO(
        v1.getMake() + " " + v1.getModel(),
        v2.getMake() + " " + v2.getModel(),
        avgFuel1, avgFuel2,
        avgEmis1, avgEmis2,
        eco1, eco2);
  }
}
