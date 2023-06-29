package com.info7255.ebl.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Task {
    private String id;
    private String title;
    private String description;
}
