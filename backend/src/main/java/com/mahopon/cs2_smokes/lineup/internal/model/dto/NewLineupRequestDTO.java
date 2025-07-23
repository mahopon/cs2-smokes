package com.mahopon.cs2_smokes.lineup.internal.model.dto;

import lombok.Getter;

@Getter
public class NewLineupRequestDTO {
    private int fromLocId;
    private int toLocId;
    private String videoUrl;
    private String imageUrl;
    private int mapId;
}
