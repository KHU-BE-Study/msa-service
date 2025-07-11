package msa.order.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import msa.order.dto.request.OrderRequest;
import msa.order.dto.response.OrderDetailResponse;
import msa.order.dto.response.OrderSummaryResponse;
import msa.order.service.OrderService;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;

	/**
	 * 주문 생성
	 * POST /orders
	 */
	@PostMapping
	public ResponseEntity<OrderDetailResponse> createOrder(
		@RequestBody OrderRequest request) {

		OrderDetailResponse response = orderService.createOrder(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	/**
	 * 사용자별 주문 목록 조회
	 * GET /orders?userId={userId}
	 */
	@GetMapping
	public ResponseEntity<List<OrderSummaryResponse>> getOrdersByUserId(
		@RequestParam UUID userId) {

		List<OrderSummaryResponse> orders = orderService.getOrdersByUserId(userId);
		return ResponseEntity.ok(orders);
	}

	/**
	 * 특정 주문 상세 조회
	 * GET /orders/{orderId}
	 */
	@GetMapping("/{orderId}")
	public ResponseEntity<OrderDetailResponse> getOrderById(
		@PathVariable UUID orderId) {

		OrderDetailResponse response = orderService.getDetailOrderById(orderId);
		return ResponseEntity.ok(response);
	}

	/**
	 * 주문 취소
	 * PATCH /orders/{orderId}/cancel
	 */
	@PatchMapping("/{orderId}/cancel")
	public ResponseEntity<OrderDetailResponse> cancelOrder(
		@PathVariable UUID orderId) {

		OrderDetailResponse response = orderService.cancelOrder(orderId);
		return ResponseEntity.ok(response);
	}
}
