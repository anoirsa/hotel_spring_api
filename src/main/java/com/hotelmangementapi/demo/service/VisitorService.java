package com.hotelmangementapi.demo.service;


import com.hotelmangementapi.demo.model.Room;
import com.hotelmangementapi.demo.model.Visitor;
import com.hotelmangementapi.demo.model.dtos.requests.VisitorRequest;
import com.hotelmangementapi.demo.model.dtos.responses.ResponseResult;
import com.hotelmangementapi.demo.model.dtos.responses.VisitorResponse;
import com.hotelmangementapi.demo.repository.RoomRepJpa;
import com.hotelmangementapi.demo.repository.VisitorRepJpa;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VisitorService {
    private final VisitorRepJpa visitorRepJpa;
    private final RoomRepJpa roomRepJpa;

    public ResponseResult addVisitor(VisitorRequest visitorRequest){

        Visitor visitorTobeAdded = ProjectMappingServices.mapToVisitor(visitorRequest);
        visitorRepJpa.save(visitorTobeAdded);
        boolean additionSuccessful = visitorRepJpa.existsById(visitorTobeAdded.getId());
        return ProjectServices.responseResult(additionSuccessful,
                (VisitorResponse)ProjectMappingServices.mapToVisitorResponse(visitorTobeAdded),"Error of some type");
    }


    public List<VisitorResponse> getAllVisitors() {
        return ProjectMappingServices
                .mapToListOfVisitor(visitorRepJpa.findAll());
    }

}
