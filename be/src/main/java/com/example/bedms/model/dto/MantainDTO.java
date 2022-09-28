package com.example.bedms.model.dto;

import com.example.bedms.model.Device;
import lombok.*;
import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MantainDTO {
    private int id;
    private LocalDate mantainDate;
    private String mantainContent;
    private Device mantainDevice;
}
