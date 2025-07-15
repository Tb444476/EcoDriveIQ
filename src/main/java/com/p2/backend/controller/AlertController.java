package com.p2.backend.controller;

import com.p2.backend.entity.Alert;
import com.p2.backend.repository.AlertRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/alerts")
public class AlertController {

  private final AlertRepository alertRepo;

  public AlertController(AlertRepository alertRepo) {
    this.alertRepo = alertRepo;
  }

  @GetMapping("/vehicle/{vehicleId}")
  public List<Alert> getAlertsByVehicle(@PathVariable Long vehicleId) {
    return alertRepo.findByVehicleId(vehicleId);
  }
}
