// package com.p2.backend.util;

// import com.p2.backend.entity.VehicleMetric;

// public class EcoScoreCalculator {

//   public static double calculate(VehicleMetric metric) {
//     double score = 100;

//     if (metric.getFuelUsed() > 10)
//       score -= 10;
//     if (metric.getEmissions() > 200)
//       score -= 15;
//     if (metric.getSpeed() > 120)
//       score -= 5;
//     if (metric.getEngineTemp() > 100)
//       score -= 10;

//     return Math.max(score, 0); // Clamp between 0-100
//   }
// }
package com.p2.backend.util;

import com.p2.backend.entity.VehicleMetric;

public class EcoScoreCalculator {

  public static double calculate(VehicleMetric metric) {
    double score = 100;

    // ⚙️ Penalize fuel usage (ideal: < 4 L)
    if (metric.getFuelUsed() > 4)
      score -= (metric.getFuelUsed() - 4) * 5; // e.g., 6L → -10

    // ⚙️ Penalize high emissions (ideal: < 120 g/km)
    if (metric.getEmissions() > 120)
      score -= (metric.getEmissions() - 120) * 0.2; // e.g., 150 → -6

    // ⚙️ Penalize excessive speed (ideal: < 80 km/h)
    if (metric.getSpeed() > 80)
      score -= (metric.getSpeed() - 80) * 0.5; // e.g., 100 → -10

    // ⚙️ Penalize high engine temperature (ideal: < 90 °C)
    if (metric.getEngineTemp() > 90)
      score -= (metric.getEngineTemp() - 90) * 1.0; // e.g., 100 → -10

    return Math.max(score, 0); // clamp to 0 minimum
  }
}
