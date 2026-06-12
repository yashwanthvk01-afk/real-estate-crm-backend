package crm.service;

import crm.dto.ClientDTO;
import java.util.List;

public interface ClientService {

    List<ClientDTO> getAllClients();
    // ↑ అన్ని clients తెచ్చుకోవడానికి

    ClientDTO getClientById(Long id);
    // ↑ ఒక్క client తెచ్చుకోవడానికి

    ClientDTO createClient(ClientDTO dto);
    // ↑ కొత్త client create చేయడానికి

    ClientDTO updateClient(Long id, ClientDTO dto);
    // ↑ existing client update చేయడానికి

    void deleteClient(Long id);
    // ↑ client delete చేయడానికి

    List<ClientDTO> searchByName(String name);
    // ↑ name తో search చేయడానికి
}