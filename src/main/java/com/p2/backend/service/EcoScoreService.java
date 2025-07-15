package com.p2.backend.service;

import com.p2.backend.entity.*;
import com.p2.backend.repository.*;
import com.p2.backend.util.EcoScoreCalculator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EcoScoreService {

  private final VehicleRepository vehicleRepo;
  private final VehicleMetricRepository metricRepo;
  private final EcoScoreRepository ecoScoreRepo;

  public EcoScoreService(VehicleRepository vehicleRepo, VehicleMetricRepository metricRepo,
      EcoScoreRepository ecoScoreRepo) {
    this.vehicleRepo = vehicleRepo;
    this.metricRepo = metricRepo;
    this.ecoScoreRepo = ecoScoreRepo;
  }

  public EcoScore calculateAndSaveEcoScore(Long vehicleId) {
    Vehicle vehicle = vehicleRepo.findById(vehicleId).orElseThrow();

    VehicleMetric latestMetric = metricRepo.findTopByVehicleOrderByTimestampDesc(vehicle)
        .orElseThrow(() -> new RuntimeException("No metrics found"));

    double score = EcoScoreCalculator.calculate(latestMetric);

    EcoScore ecoScore = new EcoScore();
    ecoScore.setScore(score);
    ecoScore.setTimestamp(LocalDateTime.now());
    ecoScore.setVehicle(vehicle);

    return ecoScoreRepo.save(ecoScore);
  }

  public EcoScore getLatestEcoScore(Long vehicleId) {
    return ecoScoreRepo.findTopByVehicleIdOrderByTimestampDesc(vehicleId)
        .orElseThrow(() -> new RuntimeException("EcoScore not found"));
  }

}
