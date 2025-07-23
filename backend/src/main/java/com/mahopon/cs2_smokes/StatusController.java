package com.mahopon.cs2_smokes;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(value = "/")
public class StatusController {
    @Operation(description="Check server status")
    @GetMapping
    public ResponseEntity<String> status() {
      return ResponseEntity.ok("Server up"); 
    }
}
