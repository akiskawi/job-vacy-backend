package com.manpower.backendProject.repositories;

import com.manpower.backendProject.models.request.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request,Integer> {
}
