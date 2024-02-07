package web.magazine.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.magazine.entities.Client;
import web.magazine.entities.Order;
import web.magazine.exception.BadRequestException;
import web.magazine.exception.NotFoundException;
import web.magazine.filter.ClientFilter;
import web.magazine.repository.ClientSpecification;
import web.magazine.repository.DataBaseClientRepository;
import web.magazine.repository.DataBaseOrderRepository;
import web.magazine.service.ClientService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DataBaseClientServiceImpl implements ClientService {
    private final DataBaseClientRepository dataBaseClientRepository;
    private final DataBaseOrderRepository dataBaseOrderRepository;

    @Override
    public List<Client> findWithFilter(ClientFilter filter) {
        return dataBaseClientRepository.findAll(ClientSpecification.withFilter(filter),
                PageRequest.of(filter.getPageNumber(), filter.getPageSize())).getContent();
    }

    @Override
    @Transactional
    public Client createClientWithOrder(Client client, List<Order> orders) {
        Client newClient = dataBaseClientRepository.save(client);
        for (Order order:orders){
            order.setClient(newClient);
            var existedOrder = dataBaseOrderRepository.saveAndFlush(order);
            client.addOrder(existedOrder);
        }
        return newClient;

    }

    @Override
    public List<Client> getAllClients() {
        return dataBaseClientRepository.findAll();
    }

    @Override
    public Client findById(Long id) {
        return dataBaseClientRepository.findById(id)
                .orElseThrow(()->new BadRequestException(
                        String.format("Client with \"%s\" not found!",id)
                ));
    }

    @Override
    @Transactional
    public Client createClient(Client client) {
        return dataBaseClientRepository.save(client);
    }

    @Override
    @Transactional
    public Client updateClient(Client client) {
        Client requireClient = findById(client.getId());
        return dataBaseClientRepository.saveAndFlush(requireClient);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
//        if (id==null){
//            throw new NotFoundException(String.format("Order with \"%s\" not found!",id));
//        }
        dataBaseClientRepository.deleteById(id);

    }
}
