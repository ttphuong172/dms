package com.example.bedms.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Device {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn
    private Category category;

    private LocalDate useDate;

    private String brand;
    private String model;
    private String serial;
    private String note;

    @ElementCollection
    @CollectionTable(name = "configuration", joinColumns =@JoinColumn(name = "configurationId"))
    private List<Configuration> configurationList;

    private String status;

    @ManyToOne
    @JoinColumn
    private Campus campus;

    @ManyToOne
    @JoinColumn
    private Floor floor;

    @ManyToOne
    @JoinColumn
    private Room room;


    @OneToMany(mappedBy = "mantainDevice")
    List<Mantain> mantainList;

    @OneToMany(mappedBy = "device")
    @JsonBackReference
    private List<TransferDevice> transferDeviceList;



}
