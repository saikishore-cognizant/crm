package com.crm.service.impl;

import com.crm.entity.Report;
import com.crm.repository.ReportRepository;
import com.crm.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ReportServiceTestImpl {

    @Mock
    private ReportRepository repository;

    @InjectMocks
    private ReportServiceimpl service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllReports() {
        List<Report> reports = Arrays.asList(new Report(), new Report());
        when(repository.findAll()).thenReturn(reports);

        List<Report> result = service.getAllReports();
        assertEquals(reports, result);
    }

    @Test
    public void testGetReportById() {
        Report report = new Report();
        when(repository.findById(1)).thenReturn(Optional.of(report));

        Report result = service.getReportById(1);
        assertEquals(report, result);
    }

    @Test
    public void testGetReportByIdNotFound() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.getReportById(1));
    }

    @Test
    public void testAddOrUpdateReport() {
        Report report = new Report();
        when(repository.save(report)).thenReturn(report);

        Report result = service.addOrUpdateReport(report);
        assertEquals(report, result);
    }

    @Test
    public void testDeleteReport() {
        Report report = new Report();
        when(repository.findById(1)).thenReturn(Optional.of(report));
        doNothing().when(repository).deleteById(1);

        service.deleteReport(1);
        verify(repository, times(1)).deleteById(1);
    }
}