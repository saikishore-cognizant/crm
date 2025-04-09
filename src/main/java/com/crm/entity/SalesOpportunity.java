package com.crm.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesOpportunity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long opportunityId;

    @NotNull(message = "Closing date is required")
    private Date closingDate;

    @NotNull(message = "Estimated value is required")
    private Double estimatedValue;

    @NotNull(message = "Sales stage is required")
    private String salesStage;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonBackReference
    private CustomerProfile customerProfile;
}