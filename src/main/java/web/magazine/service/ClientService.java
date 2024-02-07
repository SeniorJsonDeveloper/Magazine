package web.magazine.service;

import web.magazine.entities.Client;
import web.magazine.entities.Order;
import web.magazine.filter.ClientFilter;

import java.util.List;

public interface ClientService {
    List<Client> findWithFilter(ClientFilter filter);
    Client createClientWithOrder(Client client, List<Order> orders);
    List<Client> getAllClients();
    Client findById(Long id);
    Client createClient(Client client);
    Client updateClient(Client client);
    void deleteById(Long id);

}
