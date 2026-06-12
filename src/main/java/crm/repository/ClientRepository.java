package crm.repository;

import crm.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByNameContainingIgnoreCase(String name);
    // ↑ Name తో search చేయడానికి

    List<Client> findByStatus(String status);
    // ↑ Status తో filter చేయడానికి

    boolean existsByEmail(String email);
    // ↑ Email already exists అయిందా check చేయడానికి
}