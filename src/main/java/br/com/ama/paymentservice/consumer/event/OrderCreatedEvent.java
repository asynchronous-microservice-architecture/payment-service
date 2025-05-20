package br.com.ama.paymentservice.consumer.event;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreatedEvent {
    private UUID idOrder;
    private String customerName;
    private BigDecimal totalAmount;
}
