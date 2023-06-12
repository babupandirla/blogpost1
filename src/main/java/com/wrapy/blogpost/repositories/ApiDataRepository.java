package com.wrapy.blogpost.repositories;

import com.wrapy.blogpost.entity.ApiData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiDataRepository extends JpaRepository<ApiData,Long> {

}
