package web.magazine.dto.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import web.magazine.dto.responses.ClientResponse;
import web.magazine.dto.responses.ClientResponseList;
import web.magazine.dto.requests.UpsertClientRequest;
import web.magazine.entities.Client;

import java.util.List;
@DecoratedWith(OrderMapperDelegate.class)
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientMapper {
    Client requestToClient(UpsertClientRequest request);
    Client requestToClient(Long id,UpsertClientRequest request);
    ClientResponse response(Client client);
    List<ClientResponse> responseToList(List<Client> clients);
    ClientResponseList responseListToList(List<Client> clients);
}
