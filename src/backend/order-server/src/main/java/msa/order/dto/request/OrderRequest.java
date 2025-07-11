package msa.order.dto.request;

import java.util.List;
import java.util.UUID;

import lombok.Getter;

@Getter
public class OrderRequest {
	private UUID userId;
	private List<OrderProductRequest> products;
}