package com.wrapy.blogpost.repositories;

import com.wrapy.blogpost.entity.ApiData;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApiDataRepository extends JpaRepository<ApiData,Long> {

}
