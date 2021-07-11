package com.hotelmangementapi.demo.model;

import com.hotelmangementapi.demo.model.enums.RoomType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
@Entity(
        name = "Reservation"
)
@Table(
        name = "reservations",uniqueConstraints = @UniqueConstraint(name = "reservation_unique",columnNames =
        {"reservation_special_id"})
)

@NoArgsConstructor
@Setter
@Getter
@ToString
public class Reservation {

    @SequenceGenerator(
            name = "reservation_sequence",
            sequenceName = "reservation_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "reservation_sequence"
    )

    @Id
    private Long id;
    @Column(
            name = "reservation_special_id"
    )
    private String reservationSpecialId;
    private String reservedRoomId;
    private String visitorLastName;
    private String visitorFirstName;
    private LocalDate startingDate;
    private LocalDate endingDate;
    @Enumerated(EnumType.STRING)
    private RoomType roomType;
    private Integer reservationPrice;

    @ManyToOne
    @JoinColumn(
            name = "room_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "reservation_foreign_key"
            )

    )
    private Room roomToBeReserved;
    @ManyToOne
    @JoinColumn(
            name = "visitor_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "visitor_reservation_key"
            )
    )
    private Visitor visitorWhoReserved;


    public Reservation(String reservationSpecialId, String reservedRoomId, String visitorLastName,
                       String visitorFirstName, LocalDate startingDate, LocalDate endingDate, RoomType roomType) {
        this.reservationSpecialId = reservationSpecialId;
        this.reservedRoomId = reservedRoomId;
        this.visitorLastName = visitorLastName;
        this.visitorFirstName = visitorFirstName;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.roomType = roomType;

    }
}
