package com.ust.Expensive_application.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="Expensive")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Expensive {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    @Pattern(regexp = "Cash|Bank",message = "payment method should cash or bank")
    private String typetranscations;
    private double amount;
    private String description;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate date;
    //private double credited;
    //private double debited;
    @NotNull
    private boolean isExpense;




}
