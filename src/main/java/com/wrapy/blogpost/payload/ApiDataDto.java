package com.wrapy.blogpost.payload;

import lombok.Data;

@Data
public class ApiDataDto {
    private long id;
    private String url;
    private int status;
}
