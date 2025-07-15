package com.p2.backend.controller;

import com.p2.backend.entity.User;
import com.p2.backend.dto.VehicleDTO;
import com.p2.backend.entity.Vehicle;
import com.p2.backend.repository.UserRepository;
import com.p2.backend.repository.VehicleRepository;

import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

  private final VehicleRepository vehicleRepo;
  private final UserRepository userRepo;

  public VehicleController(VehicleRepository vehicleRepo, UserRepository userRepo) {
    this.vehicleRepo = vehicleRepo;
    this.userRepo = userRepo;
  }

  @GetMapping("")
  public List<Vehicle> getAllVehicles() {
    return vehicleRepo.findAll();
  }

  // @PostMapping
  // public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
  // return vehicleRepo.save(vehicle);
  // }

  @PostMapping
  public Vehicle createVehicle(@RequestBody VehicleDTO vehicleDTO) {
    // Find the user entity from userId
    User user = userRepo.findById(vehicleDTO.getUserId())
        .orElseThrow(() -> new RuntimeException("User not found with ID: " + vehicleDTO.getUserId()));

    // Create vehicle from DTO
    Vehicle vehicle = new Vehicle();
    vehicle.setMake(vehicleDTO.getMake());
    vehicle.setModel(vehicleDTO.getModel());
    vehicle.setYear(vehicleDTO.getYear());
    vehicle.setFuelType(vehicleDTO.getFuelType());
    vehicle.setRegistrationNumber(vehicleDTO.getRegistrationNumber());
    vehicle.setColor(vehicleDTO.getColor());
    vehicle.setUser(user); // 👉 THIS LINE sets the user_id foreign key

    return vehicleRepo.save(vehicle);
  }

  @GetMapping("/{id}")
  public Vehicle getVehicleById(@PathVariable Long id) {
    return vehicleRepo.findById(id).orElse(null);
  }

  @GetMapping("/user/{userId}")
  public List<Vehicle> getVehiclesByUserId(@PathVariable Long userId) {
    return vehicleRepo.findByUserId(userId);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateVehicle(@PathVariable Long id, @RequestBody VehicleDTO dto) {
    Optional<Vehicle> existingVehicleOpt = vehicleRepo.findById(id);
    if (existingVehicleOpt.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Optional<User> userOpt = userRepo.findById(dto.getUserId());
    if (userOpt.isEmpty()) {
      return ResponseEntity.badRequest().body("User not found with ID: " + dto.getUserId());
    }

    Vehicle vehicle = existingVehicleOpt.get();
    vehicle.setMake(dto.getMake());
    vehicle.setModel(dto.getModel());
    vehicle.setYear(dto.getYear());
    vehicle.setFuelType(dto.getFuelType());
    vehicle.setRegistrationNumber(dto.getRegistrationNumber());
    vehicle.setColor(dto.getColor());
    vehicle.setUser(userOpt.get()); // Update the user as well

    Vehicle updatedVehicle = vehicleRepo.save(vehicle);
    return ResponseEntity.ok(updatedVehicle);
  }

  @DeleteMapping("/{id}")
  public void deleteVehicle(@PathVariable Long id) {
    vehicleRepo.deleteById(id);
  }

  @DeleteMapping("/user/{userId}")
  public void deleteVehiclesByUserId(@PathVariable Long userId) {
    vehicleRepo.deleteByUser_Id(userId);
  }

}
