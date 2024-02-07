package web.magazine.repository;

import web.magazine.entities.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    List<Client> fetchAll();
    Optional<Client> findById(Long id);
    Client createClient(Client client);
    Client updateClient(Client client);
    void deleteClient(Long id);
}
