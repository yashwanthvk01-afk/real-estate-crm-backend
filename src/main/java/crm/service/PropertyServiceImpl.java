package crm.service;

import crm.dto.PropertyDTO;
import crm.entity.Property;
import crm.exception.ResourceNotFoundException;
import crm.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;

    private PropertyDTO toDTO(Property property) {
        return PropertyDTO.builder()
                .id(property.getId())
                .title(property.getTitle())
                .price(property.getPrice())
                .location(property.getLocation())
                .type(property.getType())
                .status(property.getStatus())
                .createdAt(property.getCreatedAt().toString())
                .build();
    }

    private Property toEntity(PropertyDTO dto) {
        return Property.builder()
                .title(dto.getTitle())
                .price(dto.getPrice())
                .location(dto.getLocation())
                .type(dto.getType())
                .status(dto.getStatus())
                .build();
    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        return propertyRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PropertyDTO getPropertyById(Long id) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException("Property not found: " + id));
        return toDTO(property);
    }

    @Override
    public PropertyDTO createProperty(PropertyDTO dto) {
        Property saved = propertyRepository.save(toEntity(dto));
        return toDTO(saved);
    }

    @Override
    public PropertyDTO updateProperty(Long id, PropertyDTO dto) {
        Property existing = propertyRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException("Property not found: " + id));
        existing.setTitle(dto.getTitle());
        existing.setPrice(dto.getPrice());
        existing.setLocation(dto.getLocation());
        existing.setType(dto.getType());
        if (dto.getStatus() != null) existing.setStatus(dto.getStatus());
        return toDTO(propertyRepository.save(existing));
    }

    @Override
    public void deleteProperty(Long id) {
        if (!propertyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Property not found: " + id);
        }
        propertyRepository.deleteById(id);
    }
}