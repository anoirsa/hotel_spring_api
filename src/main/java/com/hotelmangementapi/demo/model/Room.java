package com.hotelmangementapi.demo.model;


import com.hotelmangementapi.demo.model.enums.RoomType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity( name = "Room")
@Table(
        name = "rooms",
        uniqueConstraints = @UniqueConstraint(name = "room_id_unique",columnNames = "room_id")
)
public class Room {

    @Id
    @SequenceGenerator(
            name = "room_sequence",
            sequenceName = "room_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "room_sequence"
    )
    private Long id;

    @Column(name = "room_id",
            nullable = false)
    private String roomId;
    @Enumerated(EnumType.STRING)
    private RoomType roomType;
    private  int floorNum;

    private  String description;
    private Integer originalPrice;
    @OneToMany(
            mappedBy ="roomToBeReserved",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER ,
            orphanRemoval = true
    )

    private List<Reservation> reservations = new ArrayList<>();




    public Room(String roomId, RoomType roomType,
                int floorNum,
                String description) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.floorNum = floorNum;

        this.description = description;
    }


}
