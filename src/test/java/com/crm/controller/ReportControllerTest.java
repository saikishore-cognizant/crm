package com.crm.controller;

import com.crm.entity.Report;
import com.crm.service.impl.ReportServiceimpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ReportControllerTest {

    @Mock
    private ReportServiceimpl service;

    @InjectMocks
    private ReportController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllReports() {
        List<Report> reports = Arrays.asList(new Report(), new Report());
        when(service.getAllReports()).thenReturn(reports);

        ResponseEntity<List<Report>> response = controller.getAllReports();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reports, response.getBody());
    }

    @Test
    public void testGetReportById() {
        Report report = new Report();
        when(service.getReportById(1)).thenReturn(report);

        ResponseEntity<Report> response = controller.getReportById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(report, response.getBody());
    }

    @Test
    public void testAddOrUpdateReport() {
        Report report = new Report();
        when(service.addOrUpdateReport(report)).thenReturn(report);

        ResponseEntity<Report> response = controller.addOrUpdateReport(report);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(report, response.getBody());
    }

    @Test
    public void testUpdateReport() {
        Report report = new Report();
        when(service.getReportById(1)).thenReturn(report);
        when(service.addOrUpdateReport(report)).thenReturn(report);

        ResponseEntity<Report> response = controller.updateReport(1, report);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(report, response.getBody());
    }

    @Test
    public void testDeleteReport() {
        Report report = new Report();
        when(service.getReportById(1)).thenReturn(report);
        doNothing().when(service).deleteReport(1);

        ResponseEntity<Void> response = controller.deleteReport(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}