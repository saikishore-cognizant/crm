package com.crm.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reportId;

    @NotNull(message = "Generated date is required")
    private Date generatedDate;

    @NotNull(message = "Report type is required")
    private ReportType reportType;

    private String dataPoints;
    
    public enum ReportType {
        SALES,MARKETING,SUPPORT
    }
}