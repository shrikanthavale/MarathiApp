package database.service;

import database.entity.ServiceRequest;

import java.util.List;

public interface ServiceRequestService {

    ServiceRequest save(ServiceRequest serviceRequest);

    List<ServiceRequest> getServiceRequests();
}
