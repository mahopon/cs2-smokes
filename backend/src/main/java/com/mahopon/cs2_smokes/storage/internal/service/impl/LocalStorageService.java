package com.mahopon.cs2_smokes.storage.internal.service.impl;

import com.mahopon.cs2_smokes.storage.internal.model.dto.UploadRequestDTO;
import com.mahopon.cs2_smokes.storage.internal.model.dto.UploadResponseDTO;
import com.mahopon.cs2_smokes.storage.internal.service.IStorageService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("local")
public class LocalStorageService implements IStorageService {

    @Override
    public UploadResponseDTO generatePreSignedUrl(UploadRequestDTO dto) {
        return null;
    }

    @Override
    public void deleteFile(String url) {

    }
}
