package com.hotelmangementapi.demo.service;

import com.hotelmangementapi.demo.model.dtos.responses.ResponseResult;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

// Class containing static methods to help make the code abstract
public class ProjectServices {

    public static ResponseResult responseResult(boolean successful,Object object, String... errors) {
        if (successful) {
            return new ResponseResult(true,null,object);
        }
        else {
            List<String> errorList = new ArrayList<>();
            for (String i: errors) {
                errorList.add(i);
            }
            return new ResponseResult(false,errorList,null);
        }
    }

    public static String generateReservationSpecialId(LocalDate startDate, String lastName, String roomId) {

        String returnedValue = lastName.substring(0,2).toUpperCase()+"-"+roomId+"-"+startDate;
        return returnedValue;
    }

    public static String getIpAddress(HttpServletRequest request) {
        // Implementation to be done soon
        return null;
    }


}
