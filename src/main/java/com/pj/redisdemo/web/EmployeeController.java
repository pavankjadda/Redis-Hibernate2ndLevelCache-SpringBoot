package com.pj.redisdemo.web;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.DistributedObject;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.pj.redisdemo.domain.Employee;
import com.pj.redisdemo.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController
{
	private final EmployeeRepository employeeRepository;

	public EmployeeController(EmployeeRepository employeeRepository)
	{
		this.employeeRepository = employeeRepository;
	}

	@GetMapping("/find/all")
	public List<Employee> findAll()
	{
		return employeeRepository.findAll();
	}

	@GetMapping("/find/{empId}")
	public Optional<Employee> findById(@PathVariable String empId)
	{
		return employeeRepository.findById(empId);
	}

	@GetMapping("/create")
	public List<Employee> createNewEmployee()
	{
		Employee employee = new Employee();
		String empId = "emp" + new Random().nextInt();
		employee.setEmpId(empId);
		employee.setEmpName(empId);
		employeeRepository.saveAndFlush(employee);

		return employeeRepository.findAll();
	}

	@GetMapping("/clear")
	public void clear()
	{
		HazelcastInstance hazelcastInstance = HazelcastClient.newHazelcastClient();
		Collection<DistributedObject> distributedObjects = hazelcastInstance.getDistributedObjects();
		for (DistributedObject object : distributedObjects)
		{
			if (object instanceof IMap)
			{
				hazelcastInstance.getMap(object.getName()).destroy();
				System.out.println("Map destroyed=" + hazelcastInstance.getMap(object.getName()).getName());
			}
		}
		hazelcastInstance.shutdown();
	}
}
