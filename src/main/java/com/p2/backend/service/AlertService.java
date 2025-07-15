package com.p2.backend.service;

import com.p2.backend.entity.Alert;
import com.p2.backend.entity.VehicleMetric;
import com.p2.backend.repository.AlertRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AlertService {

  private final AlertRepository alertRepo;

  public AlertService(AlertRepository alertRepo) {
    this.alertRepo = alertRepo;
  }

  public void evaluateAndGenerate(VehicleMetric metric) {
    if (metric.getEngineTemp() > 100) {
      saveAlert(metric.getVehicle().getId(), "High Engine Temp", "Engine temperature exceeded 100°C.");
    }

    if (metric.getFuelUsed() > 20) {
      saveAlert(metric.getVehicle().getId(), "Poor Fuel Efficiency", "Fuel usage is higher than 20L.");
    }
  }

  private void saveAlert(Long vehicleId, String type, String message) {
    Alert alert = new Alert();
    alert.setVehicleId(vehicleId);
    alert.setType(type);
    alert.setMessage(message);
    alert.setTimestamp(LocalDateTime.now());
    alertRepo.save(alert);
  }
}
