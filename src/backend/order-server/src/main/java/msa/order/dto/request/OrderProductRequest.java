package msa.order.dto.request;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductRequest {
	private UUID productId;
	private Integer quantity;
}