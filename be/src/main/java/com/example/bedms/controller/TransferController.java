package com.example.bedms.controller;

import com.example.bedms.model.Device;
import com.example.bedms.model.Transfer;
import com.example.bedms.model.TransferDevice;
import com.example.bedms.model.dto.TransferDTO;
import com.example.bedms.model.dto.TransferRequest;
import com.example.bedms.service.DeviceService;
import com.example.bedms.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/transfers")
@CrossOrigin
public class TransferController {
    @Autowired
    private TransferService transferService;
    @Autowired
    private DeviceService deviceService;

    @GetMapping("")
    public ResponseEntity<List<TransferDTO>> findAll() {
        List<Transfer> transferList = transferService.findAll();
        List<TransferDTO> transferDTOList = new ArrayList<>();
        for (int i = 0; i < transferList.size(); i++) {
            TransferDTO transferDTO = new TransferDTO();
            transferDTO.setId(transferList.get(i).getId());
            transferDTO.setTransferDate(transferList.get(i).getTransferDate());
            transferDTO.setEvidence(transferList.get(i).getEvidence());
            transferDTO.setPersonInCharge(transferList.get(i).getPersonInCharge());
            transferDTO.setRoom(transferList.get(i).getRoom());
            transferDTO.setTransferDeviceList(transferList.get(i).getTransferDeviceList());
            transferDTOList.add(transferDTO);
        }
        Collections.sort(transferDTOList, (o1, o2) -> o2.getTransferDate().compareTo(o1.getTransferDate()));
//        Collections.sort(roomList, (o1, o2) -> o1.getName().compareTo(o2.getName()));
        return new ResponseEntity<>(transferDTOList, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<TransferDTO> findById(@PathVariable int id) {
        Transfer transfer = transferService.findById(id);
        TransferDTO transferDTO = new TransferDTO();
        transferDTO.setId(transfer.getId());
        transferDTO.setTransferDate(transfer.getTransferDate());
        transferDTO.setEvidence(transfer.getEvidence());
        transferDTO.setRoom(transfer.getRoom());
        transferDTO.setTransferDeviceList(transfer.getTransferDeviceList());
        return new ResponseEntity<>(transferDTO, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<String> save(@RequestBody TransferRequest transferRequest) {
        Transfer transfer = new Transfer();
        transfer.setTransferDate(transferRequest.getTransferDate());
        transfer.setEvidence(transferRequest.getEvidence());
        transfer.setPersonInCharge(transferRequest.getPersonInCharge());
        List<TransferDevice> transferDeviceList = new ArrayList<>();
        for (int i = 0; i < transferRequest.getDeviceList().size(); i++) {
            TransferDevice transferDevice = new TransferDevice();
            transferDevice.setTransfer(transfer);
            transferDevice.setRoom(transferRequest.getDeviceList().get(i).getRoom());


            transferRequest.getDeviceList().get(i).setRoom(transferRequest.getRoom());
            transferRequest.getDeviceList().get(i).setFloor(transferRequest.getRoom().getFloor());
            transferRequest.getDeviceList().get(i).setCampus(transferRequest.getRoom().getFloor().getCampus());
            deviceService.save(transferRequest.getDeviceList().get(i));

            transfer.setRoom(transferRequest.getRoom());

            transferDevice.setDevice(transferRequest.getDeviceList().get(i));
            transferDeviceList.add(transferDevice);

        }
        transfer.setTransferDeviceList(transferDeviceList);

        transferService.save(transfer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
