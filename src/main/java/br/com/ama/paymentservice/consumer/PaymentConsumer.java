package br.com.ama.paymentservice.consumer;

import br.com.ama.paymentservice.consumer.event.OrderCreatedEvent;
import br.com.ama.paymentservice.service.PaymentApproveService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentConsumer {

    private final PaymentApproveService paymentAproveService;

    @RabbitListener(queues = "${broker.queue.order.created.name}")
    public void listenCreatedOrderQueue(@Payload OrderCreatedEvent orderCreatedEvent){
       paymentAproveService.execute(orderCreatedEvent);
    }



}
