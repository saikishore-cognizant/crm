package com.crm.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.entity.Report;
import com.crm.repository.ReportRepository;
import com.crm.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class ReportServiceimpl {
    private static final Logger logger = LoggerFactory.getLogger(ReportServiceimpl.class);

    @Autowired
    private ReportRepository repository;

    public List<Report> getAllReports() {
        logger.debug("Fetching all reports");
        return repository.findAll();
    }

    public Report getReportById(Integer id) {
        logger.debug("Fetching report with ID: {}", id);
        return repository.findById(id).orElseThrow(() -> {
            logger.warn("Report not found with ID: {}", id);
            return new ResourceNotFoundException("Report not found with ID: " + id);
        });
    }

    public Report addOrUpdateReport(Report report) {
        logger.debug("Adding or updating report with ID: {}", report.getReportId());
        return repository.save(report);
    }

    public Report updateReport(Integer id, Report report) {
        logger.debug("Updating report with ID: {}", id);
        Report existingReport = repository.findById(id).orElseThrow(() -> {
            logger.warn("Report not found with ID: {}", id);
            return new ResourceNotFoundException("Report not found with ID: " + id);
        });
        report.setReportId(id);
        return repository.save(report);
    }

    public void deleteReport(Integer id) {
        logger.debug("Deleting report with ID: {}", id);
        Report existingReport = repository.findById(id).orElseThrow(() -> {
            logger.warn("Report not found with ID: {}", id);
            return new ResourceNotFoundException("Report not found with ID: " + id);
        });
        repository.deleteById(id);
    }
}