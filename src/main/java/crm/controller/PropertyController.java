package crm.controller;

import crm.dto.PropertyDTO;
import crm.exception.ApiResponse;
import crm.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/properties")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<PropertyDTO>>> getAllProperties() {
        return ResponseEntity.ok(
            ApiResponse.success(propertyService.getAllProperties(),
                "Properties fetched successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PropertyDTO>> getPropertyById(
            @PathVariable Long id) {
        return ResponseEntity.ok(
            ApiResponse.success(propertyService.getPropertyById(id),
                "Property fetched successfully"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PropertyDTO>> createProperty(
            @RequestBody PropertyDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
            ApiResponse.success(propertyService.createProperty(dto),
                "Property created successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PropertyDTO>> updateProperty(
            @PathVariable Long id,
            @RequestBody PropertyDTO dto) {
        return ResponseEntity.ok(
            ApiResponse.success(propertyService.updateProperty(id, dto),
                "Property updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteProperty(
            @PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.ok(
            ApiResponse.success(null, "Property deleted successfully"));
    }
}