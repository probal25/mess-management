package com.ws.probal.messmanagementapplication.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "member_vault")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberVault {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amount;
    private LocalDate date;
    @ManyToOne
    private Member paidBy;
}
