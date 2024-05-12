package org.inscraper.interestscraper.service.impl;

import net.sf.jasperreports.engine.JRException;
import org.inscraper.interestscraper.entity.Employee;
import org.inscraper.interestscraper.repository.EmployeeRepository;
import org.inscraper.interestscraper.service.EmployeeService;
import org.inscraper.interestscraper.util.EmployeeReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeReport employeeReport;
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public byte[] exportPDF() throws JRException, FileNotFoundException {
        return employeeReport.exportToPdf(employeeRepository.findAll());
    }
}
