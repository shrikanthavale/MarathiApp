package database.service;

import database.dto.ServiceRequestDto;
import database.entity.ServiceRequest;

import java.util.List;

public interface ServiceRequestService {

    ServiceRequest save(ServiceRequest serviceRequest);

    List<ServiceRequestDto> getServiceRequests();
}
