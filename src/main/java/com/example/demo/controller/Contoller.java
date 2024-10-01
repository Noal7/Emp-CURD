package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.EmpData;
import com.example.demo.service.EmpService;

@RestController
public class Contoller {
	
	@Autowired
    private EmpService empService;

	@GetMapping("/welcome")
	public String welcome(Model model) {
	    model.addAttribute("message", "welcome");
	    return "Welcome";
	}
	
    @GetMapping("/getAllEmp")
    public List<EmpData> getAllEmployees() {
        return empService.getAllEmployees();
    }
    
    @GetMapping("/empById/{id}")
    public ResponseEntity<EmpData> getEmployeeById(@PathVariable Long id) {
        return empService.getEmployeeById(id)
                .map(EmpData -> ResponseEntity.ok().body(EmpData))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/addEmp/{ID}/{name}/{email}/{salary}")
    public ResponseEntity<String> createEmployee(@PathVariable long ID, @PathVariable String name, @PathVariable String email, @PathVariable long salary) {
    	try {
            EmpData employee = new EmpData();
            employee.setID(ID);
            employee.setName(name);
            employee.setEmail(email);
            employee.setSalary(salary);
            empService.saveEmployee(employee);
            return ResponseEntity.ok("Saved Employee Details");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving employee details");
        }
       
    }
    
    
    @PutMapping("/empUpdate/{ID}/{name}/{email}/{salary}")
    public ResponseEntity<String> updateEmployee(@PathVariable long ID, @PathVariable String name, @PathVariable String email, @PathVariable long salary) {
    	EmpData employee = new EmpData();
    	return empService.getEmployeeById(ID)
                .map(EmpData -> {
                	employee.setName(name);
                	employee.setEmail(email);
                	employee.setSalary(salary);
                    empService.saveEmployee(employee);
                    return new ResponseEntity<>("Updated Employee Details", HttpStatus.OK);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
       
    }

   
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        return empService.getEmployeeById(id)
                .map(EmpData -> {
                	empService.deleteEmployee(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
