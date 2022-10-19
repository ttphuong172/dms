package com.example.bedms.model.dto;

import com.example.bedms.model.Device;
import com.example.bedms.model.Room;
import com.example.bedms.model.TransferDevice;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferDTO {
    private int id;

    private LocalDate transferDate;
    private String evidence;
    private String personInCharge;
    private Room room;

    private List<TransferDevice> transferDeviceList;
}
