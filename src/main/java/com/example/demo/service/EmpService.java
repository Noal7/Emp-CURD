package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.EmpData;
import com.example.demo.repo.EmployeeRepo;

@Service
public class EmpService {
	@Autowired
	private EmployeeRepo empRepo;
	
	public List<EmpData> getAllEmployees() {
        return empRepo.findAll();
    }

    public Optional<EmpData> getEmployeeById(Long id) {
        return empRepo.findById(id);
    }

    public EmpData saveEmployee(EmpData empData) {
        return empRepo.save(empData);
    }

    public void deleteEmployee(Long id) {
    	empRepo.deleteById(id);
    }
}
