package com.example.bedms.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate transferDate;
    private String evidence;

    @ManyToOne
    @JoinColumn
    private Room room;

    @OneToMany(mappedBy = "transfer",cascade = CascadeType.ALL)
    @JsonBackReference
    private List<TransferDevice> transferDeviceList;
}
