package com.hotelmangementapi.demo.model.dtos.requests;

import com.hotelmangementapi.demo.model.Room;
import com.hotelmangementapi.demo.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Setter
@Getter

public class VisitorRequest {


    private String firstName;
    private String lastName;
    private Gender gender;
    private String entryDay;
    private String expirationDay;
    private String roomId;
    private Room room;

    public VisitorRequest(String firstName, String lastName,
                          Gender gender, String entryDay, String expirationDay,
                          String roomId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.entryDay = entryDay;
        this.expirationDay = expirationDay;
        this.roomId = roomId;
    }
}
