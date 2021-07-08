package com.hotelmangementapi.demo.service;

import com.hotelmangementapi.demo.model.dtos.responses.ResponseResult;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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

    public static String getIpAddress(HttpServletRequest request) {
        // Implementation to be done soon
        return null;
    }
}
