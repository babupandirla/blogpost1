package com.wrapy.blogpost.service.impl;

import com.wrapy.blogpost.entity.ApiData;

import com.wrapy.blogpost.exception.ResourceNotFoundException;
import com.wrapy.blogpost.payload.ApiDataDto;
import com.wrapy.blogpost.repositories.ApiDataRepository;
import com.wrapy.blogpost.service.ApiDataService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApiDataServiceImpl implements ApiDataService {
    ApiDataRepository apiDataRepository;

    public ApiDataServiceImpl(ApiDataRepository apiDataRepository) {
        this.apiDataRepository = apiDataRepository;
    }

    @Override
    public ApiDataDto save(ApiDataDto apiDataDto) {
        ApiData apidata=mapToEntity(apiDataDto);
        ApiData response=apiDataRepository.save(apidata);
        ApiDataDto apiDataDto1=mapToDto(response);
        return apiDataDto1;

    }

    @Override
    public List<ApiDataDto> getAllApiData() {
        List<ApiData> apiData=apiDataRepository.findAll();
        return apiData.stream().map(apiData1 -> mapToDto(apiData1)).collect(Collectors.toList());
    }

    @Override
    public ApiDataDto getApiDataById(long id) {
        ApiData apiData=apiDataRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("apiData","id","null"));
        ApiDataDto apiDataDto=mapToDto(apiData);
        return apiDataDto;
    }


    @Override
    public void deleteApiData(long id) {
        ApiData apiData=apiDataRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("apiData","id","null"));
        if(!apiData.equals(null)){
            apiDataRepository.delete(apiData);
        }
    }
    private ApiData mapToEntity(ApiDataDto apiDataDto){
        ApiData apiData=new ApiData();
        apiData.setStatus(apiDataDto.getStatus());
        apiData.setUrl(apiDataDto.getUrl());
        return apiData;
    }
    private ApiDataDto mapToDto(ApiData apiData){
        ApiDataDto apiDataDto=new ApiDataDto();
        apiDataDto.setId(apiData.getId());
        apiDataDto.setUrl(apiData.getUrl());
        apiDataDto.setStatus(apiData.getStatus());
        return apiDataDto;
    }
}
