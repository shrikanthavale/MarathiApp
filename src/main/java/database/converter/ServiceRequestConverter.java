package database.converter;

import database.dto.ServiceRequestDto;
import database.entity.ServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import util.MarathiNumberFormatter;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Converter responsible for converting service request to dto.
 */
@Component
public class ServiceRequestConverter implements Converter<ServiceRequest, ServiceRequestDto> {

    private final MarathiNumberFormatter marathiNumberFormatter;

    @Autowired
    public ServiceRequestConverter() {
        marathiNumberFormatter = new MarathiNumberFormatter();
    }

    @Override
    public ServiceRequestDto convert(ServiceRequest serviceRequest) {
        ServiceRequestDto serviceRequestDto = new ServiceRequestDto();
        serviceRequestDto.setId(serviceRequest.getId());
        serviceRequestDto.setAddress(serviceRequest.getAddress());
        serviceRequestDto.setName(serviceRequest.getName());
        serviceRequestDto.setStatus(serviceRequest.getStatus());
        serviceRequestDto.setAmount(
                marathiNumberFormatter.format(
                        BigDecimal.valueOf(serviceRequest.getAmount()),
                        false
                )
        );
        serviceRequestDto.setCalculatedAmount(
                marathiNumberFormatter.format(
                        BigDecimal.valueOf(serviceRequest.getAmount()/100 * 7).setScale(2, RoundingMode.CEILING),
                        true
                )
        );
        return serviceRequestDto;
    }
}
