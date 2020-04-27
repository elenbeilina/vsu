package com.aqualen.vsu.core.services;

import com.aqualen.vsu.core.entity.Department;
import com.aqualen.vsu.core.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    public Department getById(long id) {
        return departmentRepository.getOne(id);
    }

    public Department update(Department department) {
        return departmentRepository.saveAndFlush(department);
    }

    public void delete(long id) {
        departmentRepository.deleteById(id);
    }
}