package com.wrapy.blogpost.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class StatusResponse {
    private Long id;
    public int status;
    public int count;
}
