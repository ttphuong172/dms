package com.example.bedms.model.dto;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrgDTO {
    private int campusQuantity;
    private int roomQuantity;
    private int floorQuantity;
    private int deviceQuantity;
    private List<String> statisticDeviceList;
}
