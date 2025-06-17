package msa.order.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import msa.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
