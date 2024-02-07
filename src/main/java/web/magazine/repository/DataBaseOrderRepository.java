package web.magazine.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import web.magazine.entities.Order;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


public interface DataBaseOrderRepository extends JpaRepository<Order,Long>, JpaSpecificationExecutor<Order> {
    List<Order> findByProduct(String product);
    Page<Order> findAllByProduct(String product, Pageable pageable);

}
