package com.example.bedms.service.Impl;

import com.example.bedms.model.Device;
import com.example.bedms.service.GeneralService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GeneralServiceImpl implements GeneralService {
    @Override
    public List<String> statistic(List<Device> deviceList) {
        Set<String> categorySet = new HashSet<>();
        for (int i = 0; i < deviceList.size(); i++) {
            categorySet.add(deviceList.get(i).getCategory().getName());
        }
        categorySet = new TreeSet<>(categorySet);
        List<String> statisticDeviceList = new ArrayList<>();
        for (String name : categorySet) {
            int count = 0;
            for (int j = 0; j < deviceList.size(); j++) {
                if (name == deviceList.get(j).getCategory().getName()) {
                    count++;
                }
            }
            statisticDeviceList.add(name+ ":"+ count);
        }
        return statisticDeviceList;
    }
}
