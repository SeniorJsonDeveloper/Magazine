package web.magazine.repository;

import org.apache.kafka.common.protocol.types.Field;
import org.springframework.data.jpa.domain.Specification;
import web.magazine.entities.Order;
import web.magazine.filter.OrderFilter;

import java.math.BigDecimal;
import java.time.Instant;

public interface OrderSpecification {
    static Specification<Order> withFilter(OrderFilter filter) {
        return Specification.where(byCategory(filter.getCategory()))
                .and(byProductName(filter.getProduct()))
                .and(byPriceRange(filter.getMinPrice(), filter.getMaxPrice()))
                .and(byTimeCreate(filter.getBeforeCreate()))
                .and(byTimeUpdate(filter.getBeforeUpdate()));
    };
    static Specification<Order> byCategory(String category){
        return (root, query, criteriaBuilder) -> {
            if (category==null){
                return null;
            }
            return criteriaBuilder.equal(root.get("category"),category);
        };
    }

    static Specification<Order> byProductName(String productName) {
        return (root, query, criteriaBuilder) -> {
            if (productName == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("product"), productName);
        };
    }

    static Specification<Order> byPriceRange(BigDecimal minCost, BigDecimal maxCost) {
        return (root, query, criteriaBuilder) -> {
            if (minCost == null && maxCost == null) {
                return null;
            }
            if (minCost == null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxCost);
            }
            if (maxCost == null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minCost);
            }
            return criteriaBuilder.between(root.get("price"), minCost, maxCost);

        };


    }
    static Specification<Order> byTimeCreate(Instant createdAt) {
        return (root, query, criteriaBuilder) -> {
            if (createdAt == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("created_at"), createdAt);
        };
    }
        static Specification<Order> byTimeUpdate(Instant updatedAt){
            return (root, query, criteriaBuilder) -> {
                if (updatedAt==null){
                    return null;
                }
                return criteriaBuilder.equal(root.get("created_at"),updatedAt);
            };
    }


}


