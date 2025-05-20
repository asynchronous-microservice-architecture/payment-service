package br.com.ama.paymentservice.producer;

import br.com.ama.paymentservice.producer.event.PaymentApprovedEvent;
import br.com.ama.paymentservice.producer.event.PaymentRejectedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${broker.queue.payment.approved.name}")
    private String paymentApprovedRoutingKey;
    @Value("${broker.queue.payment.rejected.name}")
    private String paymentRejectedRoutingKey;

    public void publishPaymentApprovedEvent(PaymentApprovedEvent paymentApprovedEvent) {

        rabbitTemplate.convertAndSend("", paymentApprovedRoutingKey, paymentApprovedEvent);
    }

    public void publishPaymentRejectedEvent(PaymentRejectedEvent paymentRejectedEvent) {

        rabbitTemplate.convertAndSend("", paymentRejectedRoutingKey, paymentRejectedEvent);
    }


}
