package com.crm.entity;

import lombok.Data;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@Entity
@Table(name = "customer_profiles")
public class CustomerProfile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "Contact Info cannot be empty")
    private String contactInfo;
    
    @NotEmpty(message = "Purchase History cannot be empty")
    private String purchaseHistory;

    @NotEmpty(message = "Segmentation Data cannot be empty")
    private String segmentationData;

    @OneToMany(mappedBy = "customerProfile", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<SalesOpportunity> salesOpportunities;

    @OneToMany(mappedBy = "customerProfile", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<SupportTicket> supportTickets;
}