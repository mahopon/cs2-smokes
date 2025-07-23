package com.mahopon.cs2_smokes.storage.internal.controller;

import com.mahopon.cs2_smokes.storage.internal.model.dto.UploadRequestDTO;
import com.mahopon.cs2_smokes.storage.internal.model.dto.UploadResponseDTO;
import com.mahopon.cs2_smokes.storage.internal.service.IStorageService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/storage")
public class StorageController {
    private final IStorageService storageService;

    public StorageController(IStorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/generateurl")
    public ResponseEntity<UploadResponseDTO> generatePreSignUrl(@Valid @ModelAttribute UploadRequestDTO dto) {
        UploadResponseDTO response = storageService.generatePreSignedUrl(dto);
        return ResponseEntity.ok(response);
    }
 }
