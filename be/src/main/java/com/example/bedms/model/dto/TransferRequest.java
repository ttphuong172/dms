package com.example.bedms.model.dto;

import com.example.bedms.model.Device;
import com.example.bedms.model.Room;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequest {
    private LocalDate transferDate;
    private String evidence;
    private Room room;
    private List<Device> deviceList;
}
