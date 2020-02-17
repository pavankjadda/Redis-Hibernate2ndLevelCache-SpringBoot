package com.pj.redisdemo.domain;

import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Cache(region = "employeeCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Employee
{
	@Id
	private String empId;
	private String empName;
}
