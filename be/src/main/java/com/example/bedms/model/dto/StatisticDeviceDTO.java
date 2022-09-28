package com.example.bedms.model.dto;

import com.example.bedms.model.Device;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatisticDeviceDTO {
    private List<Device> deviceList;
    private List<String> statisticDeviceList;
}
