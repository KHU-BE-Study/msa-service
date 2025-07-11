package msa.order.dto.response;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderProductResponse {
	private UUID productId;
	private Integer quantity;
}
