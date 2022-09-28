package com.example.bedms.model.dto;

import lombok.*;

import java.time.LocalDate;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MantainRequestDTO {
    private LocalDate mantainDate;
    private String contentMantain;
    private String personName;
}
