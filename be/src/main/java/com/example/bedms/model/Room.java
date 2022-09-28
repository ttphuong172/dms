package com.example.bedms.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToOne
    @JoinColumn
    private Floor floor;

    @OneToMany(mappedBy = "room")
    @JsonIgnore
    List<TransferDevice> transferDeviceList;


    @OneToMany(mappedBy = "room")
    @JsonIgnore
    List<Transfer> transferList;

    @OneToMany(mappedBy = "room")
    @JsonIgnore
    List<Device> deviceList;

}
