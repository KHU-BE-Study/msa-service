package msa.order.repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import msa.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, UUID> {

	@Query(value = "select o.orderId from Order o where o.userId = :userId")
	List<UUID> findOrderIdByUserId(@Param("userId") UUID userId);

	@Query("SELECT o FROM Order o " +
		"LEFT JOIN FETCH o.orderDetails od " +
		"WHERE o.orderId = :orderId")
	Optional<Order> findOrderWithDetailsByOrderId(@Param("orderId") UUID orderId);

	Optional<Order> findById(UUID orderId);
}
