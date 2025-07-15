package com.p2.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vehicle_metrics")
public class VehicleMetric {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private double speed;
  private double fuelUsed;
  private double emissions;
  private double engineTemp;
  private LocalDateTime timestamp;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public double getSpeed() {
    return speed;
  }

  public void setSpeed(double speed) {
    this.speed = speed;
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

  public double getEngineTemp() {
    return engineTemp;
  }

  public void setEngineTemp(double engineTemp) {
    this.engineTemp = engineTemp;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public Vehicle getVehicle() {
    return vehicle;
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }

  @ManyToOne
  @JoinColumn(name = "vehicle_id")
  private Vehicle vehicle;

  // Getters and Setters
}
