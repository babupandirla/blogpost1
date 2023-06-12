package com.wrapy.blogpost.service;

import com.wrapy.blogpost.payload.ApiDataDto;

import java.util.List;

public interface ApiDataService {
    ApiDataDto save(ApiDataDto apiDataDto);
    List<ApiDataDto> getAllApiData();
    ApiDataDto getApiDataById(long id);
    void deleteApiData(long id);
}
