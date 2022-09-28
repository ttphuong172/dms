package com.example.bedms.model;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable

public class Configuration {
    @ManyToOne
    @JoinColumn
    private Type type;
    private String detail;
    private LocalDate setDate;
}
