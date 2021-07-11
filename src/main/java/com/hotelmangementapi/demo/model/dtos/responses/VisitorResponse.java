package com.hotelmangementapi.demo.model.dtos.responses;

import com.hotelmangementapi.demo.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class VisitorResponse {


    private String lastName;
    private Gender gender;


}
