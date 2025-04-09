package com.crm.service;

import java.util.List;

import com.crm.entity.Report;

public interface ReportService {
	public List<Report> getAllReports();
	public Report getReportById(Integer id);
	public Report addOrUpdateReport(Report report);
	public Report updateReport(Integer id, Report report);
	public void deleteReport(Integer id);
}
