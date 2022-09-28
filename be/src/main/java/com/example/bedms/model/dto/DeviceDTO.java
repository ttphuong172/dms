package com.example.bedms.model.dto;

import com.example.bedms.model.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDTO {
    private String id;
    private Category category;

    private LocalDate useDate;

    private String brand;
    private String model;
    private String serial;
    private String note;
    private List<Configuration> configurationList;
    private Campus campus;
    private Floor floor;
    private Room room;
    List<Mantain> mantainList;
    private List<TransferDevice> transferDeviceList;
}
