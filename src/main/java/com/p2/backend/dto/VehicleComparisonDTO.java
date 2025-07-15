package com.p2.backend.dto;

public class VehicleComparisonDTO {
  private String vehicle1Name;
  private String vehicle2Name;
  private double avgFuelUsed1;
  private double avgFuelUsed2;
  private double avgEmissions1;
  private double avgEmissions2;
  private double ecoScore1;
  private double ecoScore2;

  // ✅ Constructors
  public VehicleComparisonDTO() {
  }

  public VehicleComparisonDTO(String vehicle1Name, String vehicle2Name,
      double avgFuelUsed1, double avgFuelUsed2,
      double avgEmissions1, double avgEmissions2,
      double ecoScore1, double ecoScore2) {
    this.vehicle1Name = vehicle1Name;
    this.vehicle2Name = vehicle2Name;
    this.avgFuelUsed1 = avgFuelUsed1;
    this.avgFuelUsed2 = avgFuelUsed2;
    this.avgEmissions1 = avgEmissions1;
    this.avgEmissions2 = avgEmissions2;
    this.ecoScore1 = ecoScore1;
    this.ecoScore2 = ecoScore2;
  }

  public String getVehicle1Name() {
    return vehicle1Name;
  }

  public void setVehicle1Name(String vehicle1Name) {
    this.vehicle1Name = vehicle1Name;
  }

  public String getVehicle2Name() {
    return vehicle2Name;
  }

  public void setVehicle2Name(String vehicle2Name) {
    this.vehicle2Name = vehicle2Name;
  }

  public double getAvgFuelUsed1() {
    return avgFuelUsed1;
  }

  public void setAvgFuelUsed1(double avgFuelUsed1) {
    this.avgFuelUsed1 = avgFuelUsed1;
  }

  public double getAvgFuelUsed2() {
    return avgFuelUsed2;
  }

  public void setAvgFuelUsed2(double avgFuelUsed2) {
    this.avgFuelUsed2 = avgFuelUsed2;
  }

  public double getAvgEmissions1() {
    return avgEmissions1;
  }

  public void setAvgEmissions1(double avgEmissions1) {
    this.avgEmissions1 = avgEmissions1;
  }

  public double getAvgEmissions2() {
    return avgEmissions2;
  }

  public void setAvgEmissions2(double avgEmissions2) {
    this.avgEmissions2 = avgEmissions2;
  }

  public double getEcoScore1() {
    return ecoScore1;
  }

  public void setEcoScore1(double ecoScore1) {
    this.ecoScore1 = ecoScore1;
  }

  public double getEcoScore2() {
    return ecoScore2;
  }

  public void setEcoScore2(double ecoScore2) {
    this.ecoScore2 = ecoScore2;
  }

  // ✅ Getters & Setters
  // ... (generate via IDE or Lombok if used)
}
