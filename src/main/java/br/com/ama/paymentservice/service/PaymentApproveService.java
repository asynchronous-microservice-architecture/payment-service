package br.com.ama.paymentservice.service;

import br.com.ama.paymentservice.consumer.event.OrderCreatedEvent;
import br.com.ama.paymentservice.producer.event.PaymentApprovedEvent;
import br.com.ama.paymentservice.producer.event.PaymentRejectedEvent;
import br.com.ama.paymentservice.producer.PaymentProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentApproveService {

    private final PaymentProducer paymentProducer;

    public void execute(OrderCreatedEvent orderCreatedEvent) {

        if (orderCreatedEvent.getTotalAmount().compareTo(BigDecimal.valueOf(500.00)) <= 0) {
            PaymentApprovedEvent paymentApprovedEventBuilder = PaymentApprovedEvent.builder().approvedAt(LocalDateTime.now()).idOrder(orderCreatedEvent.getIdOrder()).build();

            paymentProducer.publishPaymentApprovedEvent(paymentApprovedEventBuilder);
        } else {
            PaymentRejectedEvent paymentRejectedEvent = PaymentRejectedEvent.builder().rejectedAt(LocalDateTime.now()).idOrder(orderCreatedEvent.getIdOrder())
                    .reason("Exceeded the amount limit.").build();

            paymentProducer.publishPaymentRejectedEvent(paymentRejectedEvent);
        }

    }

}
