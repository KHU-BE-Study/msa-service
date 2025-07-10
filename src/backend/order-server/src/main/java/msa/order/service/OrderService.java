package msa.order.service;

import java.util.List;
import java.util.UUID;

import msa.order.dto.request.OrderRequest;
import msa.order.dto.response.OrderDetailResponse;
import msa.order.dto.response.OrderSummaryResponse;

public interface OrderService {

	OrderDetailResponse createOrder(OrderRequest request);

	List<OrderSummaryResponse> getOrdersByUserId(UUID userId);

	OrderDetailResponse getDetailOrderById(UUID orderId);

	OrderDetailResponse cancelOrder(UUID orderId);
}
