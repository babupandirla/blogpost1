package com.wrapy.blogpost.service.impl;

import com.wrapy.blogpost.entity.ApiData;

import com.wrapy.blogpost.exception.ResourceNotFoundException;
import com.wrapy.blogpost.payload.ApiDataDto;
import com.wrapy.blogpost.payload.StatusResponse;
import com.wrapy.blogpost.repositories.ApiDataRepository;
import com.wrapy.blogpost.service.ApiDataService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApiDataServiceImpl implements ApiDataService {
    ApiDataRepository apiDataRepository;
    @PersistenceContext
    private EntityManager em;

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
        return null;
    }


    public List<StatusResponse> getApiDataStatus() {
        HashMap<Integer,Long> h1=new HashMap<>();
        List<StatusResponse> statusData=new ArrayList<>();
        List<Object[]> resultList = em.createNativeQuery("SELECT count(*), ap.status FROM api_data  ap GROUP BY ap.status" ).getResultList();
        for(Object[] a1:resultList){
/*            Integer key=Integer.parseInt(String.valueOf(a1[1]));
            Long value=Long.parseLong(String.valueOf(a1[0]));
            h1.put(key,value);*/
            StatusResponse s1=new StatusResponse();
            s1.setStatus(Integer.parseInt(String.valueOf(a1[1])));
            s1.setCount(Integer.parseInt(String.valueOf(a1[0])));
            statusData.add(s1);
        }
        return statusData;
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
        apiDataDto.setExecutionTime(apiData.getExecutionTime());
        apiDataDto.setStartDate(dateToString(apiData.getStartDate()));
        apiDataDto.setEndDate(dateToString(apiData.getEndDate()));
        return apiDataDto;
    }
    private String dateToString(LocalDateTime localDateTime){
        return String.valueOf(localDateTime.getYear())+
                "-"+String.valueOf(localDateTime.getMonth())+
                "-"+String.valueOf(localDateTime.getDayOfMonth());
    }
}
