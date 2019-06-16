package database.service.impl;

import database.converter.ServiceRequestConverter;
import database.dto.ServiceRequestDto;
import database.entity.ServiceRequest;
import database.repository.ServiceRequestRepository;
import database.service.ServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceRequestServiceImpl implements ServiceRequestService {

    private final ServiceRequestRepository serviceRequestRepository;
    private final ServiceRequestConverter serviceRequestConverter;

    /**
     * User service initialization.
     *
     * @param serviceRequestRepository service request repository.
     * @param serviceRequestConverter converted to convert entity to dto.
     */
    @Autowired
    public ServiceRequestServiceImpl(final ServiceRequestRepository serviceRequestRepository,
                                     final ServiceRequestConverter serviceRequestConverter) {
        this.serviceRequestRepository = serviceRequestRepository;
        this.serviceRequestConverter = serviceRequestConverter;
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
    public List<ServiceRequestDto> getServiceRequests() {
        List<ServiceRequest> serviceRequests = serviceRequestRepository.findAll();
        return serviceRequests.stream()
                .map(serviceRequestConverter::convert)
                .collect(Collectors.toList());
    }
}
