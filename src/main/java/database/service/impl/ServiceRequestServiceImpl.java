package database.service.impl;

import database.entity.ServiceRequest;
import database.repository.ServiceRequestRepository;
import database.service.ServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceRequestServiceImpl implements ServiceRequestService {

    private final ServiceRequestRepository serviceRequestRepository;

    /**
     * User service initialization.
     *
     * @param serviceRequestRepository service request repository.
     */
    @Autowired
    public ServiceRequestServiceImpl(final ServiceRequestRepository serviceRequestRepository) {
        this.serviceRequestRepository = serviceRequestRepository;
    }

    @Override
    public ServiceRequest save(ServiceRequest serviceRequest) {
        if (serviceRequest.getId() > 0) {
            return serviceRequestRepository.save(serviceRequest);
        }

        serviceRequest.setStatus("प्रलंबित");
        return serviceRequestRepository.save(serviceRequest);
    }

    @Override
    public List<ServiceRequest> getServiceRequests() {
        return serviceRequestRepository.findAll();
    }
}
