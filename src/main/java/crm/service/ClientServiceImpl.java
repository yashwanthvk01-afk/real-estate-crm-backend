package crm.service;

import crm.dto.ClientDTO;
import crm.entity.Client;
import crm.exception.ResourceNotFoundException;
import crm.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    // Entity → DTO
    private ClientDTO toDTO(Client client) {
        return ClientDTO.builder()
                .id(client.getId())
                .name(client.getName())
                .phone(client.getPhone())
                .email(client.getEmail())
                .address(client.getAddress())
                .status(client.getStatus())
                .createdAt(client.getCreatedAt().toString())
                .build();
    }

    // DTO → Entity
    private Client toEntity(ClientDTO dto) {
        return Client.builder()
                .name(dto.getName())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .address(dto.getAddress())
                .status(dto.getStatus())
                .build();
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException("Client not found: " + id));
        return toDTO(client);
    }

    @Override
    public ClientDTO createClient(ClientDTO dto) {
        if (clientRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException(
                "Email already exists: " + dto.getEmail());
        }
        Client saved = clientRepository.save(toEntity(dto));
        return toDTO(saved);
    }

    @Override
    public ClientDTO updateClient(Long id, ClientDTO dto) {
        Client existing = clientRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException("Client not found: " + id));
        existing.setName(dto.getName());
        existing.setPhone(dto.getPhone());
        existing.setEmail(dto.getEmail());
        existing.setAddress(dto.getAddress());
        if (dto.getStatus() != null) existing.setStatus(dto.getStatus());
        return toDTO(clientRepository.save(existing));
    }

    @Override
    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Client not found: " + id);
        }
        clientRepository.deleteById(id);
    }

    @Override
    public List<ClientDTO> searchByName(String name) {
        return clientRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}