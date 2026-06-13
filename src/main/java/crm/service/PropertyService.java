package crm.service;

import crm.dto.PropertyDTO;
import java.util.List;

public interface PropertyService {
    List<PropertyDTO> getAllProperties();
    PropertyDTO getPropertyById(Long id);
    PropertyDTO createProperty(PropertyDTO dto);
    PropertyDTO updateProperty(Long id, PropertyDTO dto);
    void deleteProperty(Long id);
}