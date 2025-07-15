package com.p2.backend.controller;

import com.p2.backend.entity.EcoScore;
import com.p2.backend.service.EcoScoreService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/eco-score")
public class EcoScoreController {

  private final EcoScoreService ecoScoreService;

  public EcoScoreController(EcoScoreService ecoScoreService) {
    this.ecoScoreService = ecoScoreService;
  }

  @PostMapping("/calculate/{vehicleId}")
  public EcoScore calculateEcoScore(@PathVariable Long vehicleId) {
    return ecoScoreService.calculateAndSaveEcoScore(vehicleId);
  }

  @GetMapping("/{vehicleId}")
  public EcoScore getLatestEcoScore(@PathVariable Long vehicleId) {
    return ecoScoreService.getLatestEcoScore(vehicleId);
  }

}
