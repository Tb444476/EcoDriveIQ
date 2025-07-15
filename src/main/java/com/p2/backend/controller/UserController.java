// package com.p2.backend.controller;

// import com.p2.backend.dto.UserLoginDTO;
// import com.p2.backend.entity.User;
// import com.p2.backend.repository.UserRepository;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.time.LocalDate;
// import java.util.List;
// import java.util.Optional;

// @CrossOrigin
// @RestController
// @RequestMapping("/api/users")
// public class UserController {

//   private final UserRepository userRepo;

//   public UserController(UserRepository userRepo) {
//     this.userRepo = userRepo;
//   }

//   @GetMapping
//   public List<User> getAllUsers() {
//     return userRepo.findAll();
//   }

//   @PostMapping("/register")
//   public ResponseEntity<User> registerUser(@RequestBody User user) {
//     if (userRepo.findByEmail(user.getEmail()).isPresent()) {
//       return new ResponseEntity<>(HttpStatus.CONFLICT); // Email already exists
//     }
//     User savedUser = userRepo.save(user);
//     return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
//   }

//   @PostMapping
//   public User createUser(@RequestBody User user) {
//     return userRepo.save(user);
//   }

//   @GetMapping("/{id}")
//   public User getUserById(@PathVariable Long id) {
//     return userRepo.findById(id).orElse(null);
//   }

//   @PutMapping("/{id}")
//   public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
//     updatedUser.setId(id);
//     return userRepo.save(updatedUser);
//   }

//   @DeleteMapping("/{id}")
//   public void deleteUser(@PathVariable Long id) {
//     userRepo.deleteById(id);
//   }

//   @PostMapping("/login")
//   public ResponseEntity<?> loginUser(@RequestBody UserLoginDTO loginDTO) {
//     Optional<User> userOpt = userRepo.findByEmail(loginDTO.getEmail());

//     if (userOpt.isPresent() && userOpt.get().getPassword().equals(loginDTO.getPassword())) {
//       return ResponseEntity.ok(userOpt.get());
//     } else {
//       return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
//     }
//   }

// }
package com.p2.backend.controller;

import com.p2.backend.dto.UserLoginDTO;
import com.p2.backend.entity.User;
import com.p2.backend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserRepository userRepo;

  public UserController(UserRepository userRepo) {
    this.userRepo = userRepo;
  }

  @GetMapping
  public List<User> getAllUsers() {
    return userRepo.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getUserById(@PathVariable Long id) {
    return userRepo.findById(id)
        .<ResponseEntity<?>>map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found"));
  }

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@RequestBody User user) {
    if (userRepo.findByEmail(user.getEmail()).isPresent()) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already registered");
    }

    user.setRegistrationDate(LocalDate.now());
    User savedUser = userRepo.save(user);
    return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
    Optional<User> existing = userRepo.findById(id);
    if (existing.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }

    updatedUser.setId(id);
    updatedUser.setRegistrationDate(existing.get().getRegistrationDate()); // preserve original registration date
    User saved = userRepo.save(updatedUser);
    return ResponseEntity.ok(saved);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteUser(@PathVariable Long id) {
    if (userRepo.existsById(id)) {
      userRepo.deleteById(id);
      return ResponseEntity.ok("User deleted");
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }
  }

  @PostMapping("/login")
  public ResponseEntity<?> loginUser(@RequestBody UserLoginDTO loginDTO) {
    Optional<User> userOpt = userRepo.findByEmail(loginDTO.getEmail());

    if (userOpt.isPresent() && userOpt.get().getPassword().equals(loginDTO.getPassword())) {
      return ResponseEntity.ok(userOpt.get());
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
    }
  }
}
