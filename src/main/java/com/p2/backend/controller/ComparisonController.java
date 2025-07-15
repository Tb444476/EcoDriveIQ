package com.p2.backend.controller;

import com.p2.backend.dto.VehicleComparisonDTO;
import com.p2.backend.service.ComparisonService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/compare")
public class ComparisonController {

  private final ComparisonService comparisonService;

  public ComparisonController(ComparisonService comparisonService) {
    this.comparisonService = comparisonService;
  }

  @GetMapping
  public VehicleComparisonDTO compareVehicles(@RequestParam Long vid1, @RequestParam Long vid2) {
    return comparisonService.compareVehicles(vid1, vid2);
  }
}
