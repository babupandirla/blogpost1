package com.wrapy.blogpost.controller;

import com.wrapy.blogpost.entity.ApiData;
import com.wrapy.blogpost.repositories.ApiDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/apidata")
public class ApiDataController {
    private final ApiDataRepository apiDataRepository;

    @Autowired
    public ApiDataController(ApiDataRepository apiDataRepository) {
        this.apiDataRepository = apiDataRepository;
    }

    @GetMapping
    public List<ApiData> getAllApiData() {
        return apiDataRepository.findAll();
    }
    @PostMapping
    public ApiData createApiData(@RequestBody ApiData apiData) {
        return apiDataRepository.save(apiData);
    }

    @GetMapping("/{id}")
    public ApiData getApiDataById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return apiDataRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }

    @PutMapping("/{id}")
    public ApiData updateApiData(@PathVariable Long id, @RequestBody ApiData updatedApiData) throws ChangeSetPersister.NotFoundException {
        ApiData apiData = apiDataRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        apiData.setUrl(updatedApiData.getUrl());
        apiData.setStatus(updatedApiData.getStatus());
        apiData.setStartDate(updatedApiData.getStartDate());
        apiData.setEndDate(updatedApiData.getEndDate());
        apiData.setExecutionTime(updatedApiData.getExecutionTime());

        return apiDataRepository.save(apiData);
    }

    @DeleteMapping("/{id}")
    public void deleteApiData(@PathVariable Long id) {
        apiDataRepository.deleteById(id);
    }
}
