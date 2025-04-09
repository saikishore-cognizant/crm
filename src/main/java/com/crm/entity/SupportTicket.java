package com.crm.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupportTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    @NotEmpty(message = "Assigned agent is required")
    private String assignedAgent;

    @NotEmpty(message = "Issue description is required")
    private String issueDescription;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status is required")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonBackReference
    private CustomerProfile customerProfile;

    public enum Status {
        CLOSED, OPEN
    }
}