package crm.controller;

import crm.dto.ClientDTO;
import crm.exception.ApiResponse;
import crm.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ClientDTO>>> getAllClients() {
        return ResponseEntity.ok(
            ApiResponse.success(clientService.getAllClients(),
                "Clients fetched successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ClientDTO>> getClientById(
            @PathVariable Long id) {
        return ResponseEntity.ok(
            ApiResponse.success(clientService.getClientById(id),
                "Client fetched successfully"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ClientDTO>> createClient(
            @RequestBody ClientDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
            ApiResponse.success(clientService.createClient(dto),
                "Client created successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ClientDTO>> updateClient(
            @PathVariable Long id,
            @RequestBody ClientDTO dto) {
        return ResponseEntity.ok(
            ApiResponse.success(clientService.updateClient(id, dto),
                "Client updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteClient(
            @PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok(
            ApiResponse.success(null, "Client deleted successfully"));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<ClientDTO>>> search(
            @RequestParam String name) {
        return ResponseEntity.ok(
            ApiResponse.success(clientService.searchByName(name),
                "Search results"));
    }
}