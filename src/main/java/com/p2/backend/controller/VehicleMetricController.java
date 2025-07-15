package com.p2.backend.controller;

import com.p2.backend.dto.MetricChartDTO;
import com.p2.backend.entity.VehicleMetric;
import com.p2.backend.service.VehicleMetricService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/metrics")
public class VehicleMetricController {

  private final VehicleMetricService metricService;

  public VehicleMetricController(VehicleMetricService metricService) {
    this.metricService = metricService;
  }

  @PostMapping("/simulate/{vehicleId}")
  public VehicleMetric simulateMetric(@PathVariable Long vehicleId) {
    return metricService.simulateAndSaveMetric(vehicleId);
  }

  @GetMapping("/vehicle/{vehicleId}")
  public List<MetricChartDTO> getVehicleMetrics(@PathVariable Long vehicleId) {
    return metricService.getMetricsForVehicle(vehicleId);
  }
}
