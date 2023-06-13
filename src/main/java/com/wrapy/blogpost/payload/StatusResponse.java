package com.wrapy.blogpost.payload;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class StatusResponse {
    public int status;
    public int count;
}
