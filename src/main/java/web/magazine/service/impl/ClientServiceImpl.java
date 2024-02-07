package web.magazine.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;
import web.magazine.entities.Client;
import web.magazine.entities.Order;
import web.magazine.filter.ClientFilter;
import web.magazine.repository.ClientRepository;
import web.magazine.service.ClientService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Override
    public List<Client> findWithFilter(ClientFilter filter) {
        throw new NotImplementedException();
    }

    @Override
    public Client createClientWithOrder(Client client, List<Order> orders) {
        throw new NotImplementedException();
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.fetchAll();
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(()->new RuntimeException(
                        String.format("Client with id \"%s\" not found!",id)
                ));
    }

    @Override
    public Client createClient(Client client) {
        return clientRepository.createClient(client);
    }

    @Override
    public Client updateClient(Client client) {
        return clientRepository.updateClient(client);
    }

    @Override
    public void deleteById(Long id) {
        clientRepository.deleteClient(id);
    }
}
