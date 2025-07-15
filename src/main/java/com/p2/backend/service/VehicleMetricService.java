// package com.p2.backend.service;

// import com.p2.backend.dto.MetricChartDTO;
// import com.p2.backend.entity.Vehicle;
// import com.p2.backend.entity.VehicleMetric;
// import com.p2.backend.repository.VehicleMetricRepository;
// import com.p2.backend.repository.VehicleRepository;

// import org.springframework.stereotype.Service;

// import java.time.LocalDateTime;
// import java.util.Random;
// import java.util.stream.Collectors;
// import java.util.List;

// @Service
// public class VehicleMetricService {

//   private final VehicleMetricRepository metricRepo;
//   private final VehicleRepository vehicleRepo;

//   public VehicleMetricService(VehicleMetricRepository metricRepo, VehicleRepository vehicleRepo) {
//     this.metricRepo = metricRepo;
//     this.vehicleRepo = vehicleRepo;
//   }

//   public VehicleMetric simulateAndSaveMetric(Long vehicleId) {
//     Vehicle vehicle = vehicleRepo.findById(vehicleId).orElseThrow();

//     Random rand = new Random();

//     VehicleMetric metric = new VehicleMetric();
//     metric.setVehicle(vehicle);
//     metric.setSpeed(40 + rand.nextDouble() * 60); // 40 to 100 km/h
//     metric.setFuelUsed(1 + rand.nextDouble() * 5); // 1 to 6 liters
//     metric.setEmissions(100 + rand.nextDouble() * 50); // 100 to 150 g/km
//     metric.setEngineTemp(70 + rand.nextDouble() * 30); // 70 to 100 °C
//     metric.setTimestamp(LocalDateTime.now());
//     alertService.evaluateAndGenerate(savedMetrics);

//     return metricRepo.save(metric);
//   }

//   public List<MetricChartDTO> getMetricsForVehicle(Long vehicleId) {
//     List<VehicleMetric> metrics = metricRepo.findLatestMetricsByVehicleId(vehicleId);
//     return metrics.stream()
//         .map(vm -> new MetricChartDTO(vm.getTimestamp(), vm.getFuelUsed(), vm.getEmissions(), vm.getSpeed(),
//             vm.getEngineTemp()))
//         .collect(Collectors.toList());
//   }
// }
package com.p2.backend.service;

import com.p2.backend.dto.MetricChartDTO;
import com.p2.backend.entity.Vehicle;
import com.p2.backend.entity.VehicleMetric;
import com.p2.backend.repository.VehicleMetricRepository;
import com.p2.backend.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class VehicleMetricService {

  private final VehicleMetricRepository metricRepo;
  private final VehicleRepository vehicleRepo;
  private final AlertService alertService; // ✅ Inject AlertService

  public VehicleMetricService(VehicleMetricRepository metricRepo, VehicleRepository vehicleRepo,
      AlertService alertService) {
    this.metricRepo = metricRepo;
    this.vehicleRepo = vehicleRepo;
    this.alertService = alertService; // ✅ Assign here
  }

  public VehicleMetric simulateAndSaveMetric(Long vehicleId) {
    Vehicle vehicle = vehicleRepo.findById(vehicleId).orElseThrow();

    Random rand = new Random();

    VehicleMetric metric = new VehicleMetric();
    metric.setVehicle(vehicle);
    metric.setSpeed(40 + rand.nextDouble() * 60); // 40 to 100 km/h
    metric.setFuelUsed(1 + rand.nextDouble() * 5); // 1 to 6 liters
    metric.setEmissions(100 + rand.nextDouble() * 50); // 100 to 150 g/km
    metric.setEngineTemp(70 + rand.nextDouble() * 30); // 70 to 100 °C

    // metric.setEngineTemp(105.0); // triggers high temp alert
    // metric.setFuelUsed(25.0); // triggers poor fuel efficiency alert

    metric.setTimestamp(LocalDateTime.now());

    // ✅ Save first
    VehicleMetric savedMetric = metricRepo.save(metric);

    // ✅ Then generate alerts based on this saved metric
    alertService.evaluateAndGenerate(savedMetric);

    return savedMetric;
  }

  public List<MetricChartDTO> getMetricsForVehicle(Long vehicleId) {
    List<VehicleMetric> metrics = metricRepo.findLatestMetricsByVehicleId(vehicleId);
    return metrics.stream()
        .map(vm -> new MetricChartDTO(vm.getTimestamp(), vm.getFuelUsed(), vm.getEmissions(), vm.getSpeed(),
            vm.getEngineTemp()))
        .collect(Collectors.toList());
  }
}
