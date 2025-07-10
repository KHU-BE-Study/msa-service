package msa.order.dto.response;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderDetailResponse {
	private UUID orderId;
	private UUID userId;
	private String status;
	private LocalDateTime createTime;
	private List<OrderProductResponse> orderDetails;
}
