package com.crm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.crm.entity.Report;
import com.crm.service.impl.ReportServiceimpl;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
@Validated
public class ReportController {
    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
    
    @Autowired
    private ReportServiceimpl service;

    @GetMapping
    public ResponseEntity<List<Report>> getAllReports() {
        logger.info("Fetching all reports");
        List<Report> reports = service.getAllReports();
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable Integer id) {
        logger.info("Fetching report with ID: {}", id);
        Report report = service.getReportById(id);
        if (report != null) {
            return new ResponseEntity<>(report, HttpStatus.OK);
        } else {
            logger.warn("Report with ID: {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Report> addOrUpdateReport(@Valid @RequestBody Report report) {
        logger.info("Adding or updating report with ID: {}", report.getReportId());
        Report savedReport = service.addOrUpdateReport(report);
        return new ResponseEntity<>(savedReport, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Report> updateReport(@PathVariable Integer id, @Valid @RequestBody Report report) {
        logger.info("Updating report with ID: {}", id);
        Report existingReport = service.getReportById(id);
        if (existingReport != null) {
            report.setReportId(id);
            Report updatedReport = service.addOrUpdateReport(report);
            return new ResponseEntity<>(updatedReport, HttpStatus.OK);
        } else {
            logger.warn("Report with ID: {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Integer id) {
        logger.info("Deleting report with ID: {}", id);
        Report existingReport = service.getReportById(id);
        if (existingReport != null) {
            service.deleteReport(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            logger.warn("Report with ID: {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}