package net.atlassian.vastidev.mscards;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = MscardsApplication.class)
public class RabbitMQTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendMessage() {
        assertNotNull(rabbitTemplate, "RabbitTemplate is not injected");

        String queueName = "emissao-cards";
        String testMessage = "Test message";
        rabbitTemplate.convertAndSend(queueName, testMessage);
        System.out.println("Message sent to the queue: " + testMessage);
    }

    @Test
    public void testReceiveMessage() {
        assertNotNull(rabbitTemplate, "RabbitTemplate is not injected");

        String queueName = "emissao-cards";
        String receivedMessage = (String) rabbitTemplate.receiveAndConvert(queueName);
        System.out.println("Message received from the queue: " + receivedMessage);
    }
}

