package it.salestaxes.main;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		try(ConfigurableApplicationContext  ctx = new ClassPathXmlApplicationContext("spring-context.xml")){
			MockClient client = (MockClient) ctx.getBean("mockClient");
			client.doFirstShopping();
			client.doSecondShopping();
			client.doThirdShopping();
		}

	}


}
