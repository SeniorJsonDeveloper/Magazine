package web.magazine.repository;

import org.springframework.data.jpa.domain.Specification;
import web.magazine.entities.Client;
import web.magazine.filter.ClientFilter;

public interface ClientSpecification {
    static Specification<Client> withFilter(ClientFilter filter){
        return Specification.where(byUserName(filter.getUsername()));
    }
    static Specification<Client> byUserName(String userName){
        return (root, query, criteriaBuilder) -> {
            if (userName==null){
                return null;
            }
            return criteriaBuilder.equal(root.get("username"),userName);
        };
    }
}
