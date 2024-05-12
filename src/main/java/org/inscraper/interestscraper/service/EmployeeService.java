package org.inscraper.interestscraper.service;

import net.sf.jasperreports.engine.JRException;
import org.inscraper.interestscraper.entity.Employee;

import java.io.FileNotFoundException;
import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    byte[] exportPDF() throws JRException, FileNotFoundException;
}
