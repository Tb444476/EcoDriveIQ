package com.p2.backend.dto;

import java.time.LocalDateTime;

public class MetricChartDTO {
  private LocalDateTime timestamp;
  private double fuelUsed;
  private double emissions;
  private double speed;
  private double engineTemp;

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public double getFuelUsed() {
    return fuelUsed;
  }

  public void setFuelUsed(double fuelUsed) {
    this.fuelUsed = fuelUsed;
  }

  public double getEmissions() {
    return emissions;
  }

  public void setEmissions(double emissions) {
    this.emissions = emissions;
  }

  public double getSpeed() {
    return speed;
  }

  public void setSpeed(double speed) {
    this.speed = speed;
  }

  public double getEngineTemp() {
    return engineTemp;
  }

  public void setEngineTemp(double engineTemp) {
    this.engineTemp = engineTemp;
  }

  // Constructors
  public MetricChartDTO() {
  }

  public MetricChartDTO(LocalDateTime timestamp, double fuelUsed, double emissions, double speed, double engineTemp) {
    this.timestamp = timestamp;
    this.fuelUsed = fuelUsed;
    this.emissions = emissions;
    this.speed = speed;
    this.engineTemp = engineTemp;
  }

  // Getters and setters
  // (generate all)
}
