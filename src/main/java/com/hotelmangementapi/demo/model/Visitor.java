package com.hotelmangementapi.demo.model;


import com.hotelmangementapi.demo.model.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

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
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(
            mappedBy = "visitorWhoReserved",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL ,
            orphanRemoval = true
    )
    private List<Reservation> reservations = new ArrayList<>();
    public Visitor(String firstName,
                   String lastName, Gender gender


                   ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;


    }
}
