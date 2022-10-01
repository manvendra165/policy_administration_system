package com.cognizant.consumer.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cognizant.consumer.response.MessageResponse;


@ExtendWith(MockitoExtension.class)
public class MessageResponseTests {
	
	MessageResponse messageResponse;
	
	@Test
    public void testConsumerBusinessDetails() throws Exception {
		messageResponse=new MessageResponse();
		messageResponse.setMessage("test");
		assertEquals("test",messageResponse.getMessage());
	}
	
	@Test
    public void testConsumerBusinessDetailsConstructor() throws Exception {
		messageResponse=new MessageResponse("test");
		assertEquals("test",messageResponse.getMessage());
	}
}
