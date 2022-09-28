package com.example.bedms.service;

import com.example.bedms.model.Device;

import java.util.List;

public interface GeneralService {
    List<String> statistic(List<Device> deviceList);
}
