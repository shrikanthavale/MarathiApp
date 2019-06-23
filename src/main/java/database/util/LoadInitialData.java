package database.util;

import database.entity.ServiceRequest;
import database.service.ServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class LoadInitialData {

    private final ServiceRequestService serviceRequestService;

    /**
     * Default constructor.
     *
     * @param serviceRequestService service request to load default data.
     */
    @Autowired
    public LoadInitialData(ServiceRequestService serviceRequestService) {
        this.serviceRequestService = serviceRequestService;
    }

    @EventListener
    public void loadDefaultData(ApplicationReadyEvent event) {
        ServiceRequest serviceRequest1 = new ServiceRequest(
                "श्रीकांत हावळे",
                "खडकी, पुणे",
                1234.56,
                "प्रलंबित"
        );
        serviceRequestService.save(serviceRequest1);
        ServiceRequest serviceRequest2 = new ServiceRequest(
                "प्रकाश शिंदे",
                "ठाणे",
                2000,
                "प्रलंबित"
        );
        serviceRequestService.save(serviceRequest2);
    }
}
