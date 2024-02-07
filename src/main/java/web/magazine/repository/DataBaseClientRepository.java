package web.magazine.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import web.magazine.entities.Client;

@Repository
public interface DataBaseClientRepository extends JpaRepository<Client,Long>, JpaSpecificationExecutor<Client> {
    Page<Client> findAllByUsername(String username, Pageable pageable);
}
