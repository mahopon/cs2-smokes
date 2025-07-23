package com.mahopon.cs2_smokes.storage.internal.service;

import com.mahopon.cs2_smokes.storage.api.service.IStorageAPI;
import com.mahopon.cs2_smokes.storage.internal.model.dto.UploadRequestDTO;
import com.mahopon.cs2_smokes.storage.internal.model.dto.UploadResponseDTO;

public interface IStorageService extends IStorageAPI {
    UploadResponseDTO generatePreSignedUrl(UploadRequestDTO dto);
}
