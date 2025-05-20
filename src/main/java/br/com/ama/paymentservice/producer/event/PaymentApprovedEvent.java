package br.com.ama.paymentservice.producer.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PaymentApprovedEvent {

    private UUID idOrder;
    private LocalDateTime approvedAt;

}
