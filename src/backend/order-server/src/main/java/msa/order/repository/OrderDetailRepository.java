package msa.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import msa.order.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
