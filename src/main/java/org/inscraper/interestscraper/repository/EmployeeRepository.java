package org.inscraper.interestscraper.repository;

import org.inscraper.interestscraper.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
