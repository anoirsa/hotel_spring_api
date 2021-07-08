package com.hotelmangementapi.demo.model;


import com.hotelmangementapi.demo.model.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(
        name = "Visitor"
)
@Table(
        name = "visitors"
)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Visitor {
    @Id
    @SequenceGenerator(
            name = "visitor_sequence",
            sequenceName = "visitor_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "visitor_sequence"

    )
    private Long id;
    private String firstName;
    private String lastName;
    private String roomSpecialId;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDate entryDay;
    private LocalDate expirationDay;

    @ManyToOne
    @JoinColumn(
            name = "room_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "visitor_room_fk"
            )
    )
    private Room room;

    public Visitor(String firstName,
                   String lastName, Gender gender,
                   LocalDate entryDay, LocalDate expirationDay,
                   Room room,
                   String roomSpecialId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.entryDay = entryDay;
        this.expirationDay = expirationDay;
        this.room = room;
    }
}
