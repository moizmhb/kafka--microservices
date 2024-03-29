package com.eta.order.producer;

import com.eta.common.entity.Order;
import com.eta.order.entity.OrderDAO;
import com.eta.order.testUtil.ListenerPayloadChecker;
import com.eta.order.util.OrderConverter;
import com.eta.order.util.OrderGenerator;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@DirtiesContext
class KafkaProducerTest {

    @Autowired
    private KafkaProducer kafkaProducer;
    @Autowired
    ListenerPayloadChecker listenerPayloadChecker;
    @Autowired
    OrderGenerator orderGenerator;
    @Autowired
    OrderConverter orderConverter;

    static {
        System.setProperty("spring.kafka.streams.state-dir", "/tmp/kafka-streams/"+ UUID.randomUUID());
    }

    @Test
    void send_eventProduced_isSent() throws InterruptedException {
//        String orderID = "orderkey1";
//        String itemID = "itemkey1";
//
//        Order order = new Order(orderID, 2.99);
//        Item item = new Item(itemID, 4);
//        order.setItems(List.of(item));
//
//        kafkaProducer.sendOrder(order.getOrderNumber(), order);
//        listenerPayloadChecker.getLatch().await(10000, TimeUnit.MILLISECONDS);
//        ConsumerRecord<String, Order> result = listenerPayloadChecker.getConsumerRecord();
//        List<Item> items = result.value().getItems();
//
//        assertThat(result).isNotNull();
//        assertThat(items).isNotNull();
//        System.out.println();
    }

    @Test
    @Ignore
    void generalTests() {
        List<OrderDAO> orderDAOList = orderGenerator.generateNewOrderDAOs(Optional.of(2L), Optional.of(1L));
        List<Order> orders = orderConverter.listOrderDaoToKafka(orderDAOList);
        orders.forEach(order -> kafkaProducer.sendOrder(order.getOrderNumber(),order));
    }
}