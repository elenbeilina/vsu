package com.aqualen.vsu.repository;

import com.aqualen.vsu.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
