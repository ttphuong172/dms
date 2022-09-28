package com.example.bedms.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn
    private Room room;


    @ManyToOne
    @JoinColumn
    private Device device;

    @ManyToOne
    @JoinColumn
    private Transfer transfer;


}
