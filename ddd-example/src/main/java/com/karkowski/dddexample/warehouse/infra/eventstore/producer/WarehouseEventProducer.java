package com.karkowski.dddexample.warehouse.infra.eventstore.producer;

import com.karkowski.dddexample.warehouse.domain.event.Event;
import com.karkowski.dddexample.warehouse.infra.eventstore.config.KafkaTopicsConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
@RequiredArgsConstructor
@Slf4j
public class WarehouseEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void createEvent(final Event event) {
        final String message = event.toString();
        ListenableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(event.getEventName(), event.toString());

        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(final SendResult<String, String> result) {
                log.info("Message [{}] delivered with offset {}",
                        message,
                        result.getRecordMetadata().offset());
            }
            @Override
            public void onFailure(final Throwable ex) {
                log.warn("Unable to deliver message [{}]. {}",
                        message,
                        ex.getMessage());
            }
        });
    }
}
