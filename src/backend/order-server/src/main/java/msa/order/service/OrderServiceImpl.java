package msa.order.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import msa.order.dto.request.OrderRequest;
import msa.order.dto.response.OrderDetailResponse;
import msa.order.dto.response.OrderProductResponse;
import msa.order.dto.response.OrderSummaryResponse;
import msa.order.entity.Order;
import msa.order.entity.OrderDetail;
import msa.order.entity.OrderStatus;
import msa.order.repository.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;

	@Override
	public OrderDetailResponse createOrder(OrderRequest request) {

		Order order = new Order();
		order.setUserId(request.getUserId());
		order.setStatus(OrderStatus.PENDING);
		order.setOrderTime(LocalDateTime.now());

		List<OrderDetail> details = request.getProducts().stream()
			.map(p -> {
				OrderDetail detail = new OrderDetail();
				detail.setOrder(order);
				detail.setProductId(p.getProductId());
				detail.setQuantity(p.getQuantity());
				return detail;
			})
			.toList();

		order.setOrderDetails(details);

		Order savedOrder = orderRepository.save(order);

		List<OrderProductResponse> productResponses = savedOrder.getOrderDetails().stream().
			map(d -> new OrderProductResponse(d.getProductId(), d.getQuantity()))
			.toList();

		return new OrderDetailResponse(
			savedOrder.getOrderId(),
			savedOrder.getUserId(),
			savedOrder.getStatus().name(),
			savedOrder.getOrderTime(),
			productResponses
		);
	}

	@Override
	public List<OrderSummaryResponse> getOrdersByUserId(UUID userId) {
		List<UUID> orderIds = orderRepository.findOrderIdByUserId(userId);

		return orderIds.stream()
			.map(id -> {
				Order order = orderRepository.findById(id)
					.orElseThrow(() -> new RuntimeException("주문이 없습니다. id=" + id));
				return new OrderSummaryResponse(
					order.getOrderId(),
					order.getStatus().name()
				);
			})
			.collect(Collectors.toList());
	}

	@Override
	public OrderDetailResponse getDetailOrderById(UUID orderId) {
		Order order = orderRepository.findOrderWithDetailsByOrderId(orderId)
			.orElseThrow(() -> new RuntimeException("주문을 찾을 수 없습니다. id = "+orderId));

		List<OrderProductResponse> productResponses = order.getOrderDetails().stream()
			.map(d -> new OrderProductResponse(d.getProductId(), d.getQuantity()))
			.toList();

		return new OrderDetailResponse(
			order.getOrderId(),
			order.getUserId(),
			order.getStatus().name(),
			order.getOrderTime(),
			productResponses
		);
	}

	@Override
	public OrderDetailResponse cancelOrder(UUID orderId) {
		Order order = orderRepository.findById(orderId)
			.orElseThrow(() -> new RuntimeException("주문을 찾을 수 없습니다. id=" + orderId));

		order.setStatus(OrderStatus.CANCELLED);
		orderRepository.save(order);

		List<OrderProductResponse> productResponses = order.getOrderDetails().stream()
			.map(d -> new OrderProductResponse(d.getProductId(), d.getQuantity()))
			.collect(Collectors.toList());

		return new OrderDetailResponse(
			order.getOrderId(),
			order.getUserId(),
			order.getStatus().name(),
			order.getOrderTime(),
			productResponses
		);
	}
}
