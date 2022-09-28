package com.example.bedms.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mantain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate mantainDate;
    private String mantainContent;
    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Device mantainDevice;
    private String personName;
}
