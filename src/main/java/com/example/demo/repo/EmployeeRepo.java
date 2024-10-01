package com.example.demo.repo;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.EmpData;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EmployeeRepo extends JpaRepository<EmpData,Long> {

}
