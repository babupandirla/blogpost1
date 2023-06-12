package com.wrapy.blogpost.repositories;

import com.wrapy.blogpost.entity.StatusResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StatusResponseRepository extends JpaRepository<StatusResponse,Long> {
    public static final String FIND_PROJECTS = "SELECT count(*), ap.status FROM api_data ap GROUP BY ap.status";

    @Query(value = FIND_PROJECTS, nativeQuery = true)
    public List<StatusResponse> findApiDataByStatus();
}
