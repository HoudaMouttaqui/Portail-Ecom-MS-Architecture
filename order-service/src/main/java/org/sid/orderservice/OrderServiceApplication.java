package org.sid.orderservice;

import org.aspectj.weaver.ast.Or;
import org.sid.orderservice.entities.OderItem;
import org.sid.orderservice.entities.Order;
import org.sid.orderservice.enums.OrderStatus;
import org.sid.orderservice.repositories.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(OrderRepository orderRepository, RepositoryRestConfiguration restConfiguration){
		return args -> {
			restConfiguration.exposeIdsFor(Order.class);
			orderRepository.saveAll(
					List.of(
							Order.builder().orderDate(LocalDate.of(2023,1,4)).orderStatus(OrderStatus.PENDING).totalAmount(1200).build(),
							Order.builder().orderDate(LocalDate.of(2022,3,10)).orderStatus(OrderStatus.PROCESSED).totalAmount(147).build(),
							Order.builder().orderDate(LocalDate.of(2021,6,5)).orderStatus(OrderStatus.CANCELLED).totalAmount(12000).build(),
							Order.builder().orderDate(LocalDate.of(2023,1,6)).orderStatus(OrderStatus.PAID).totalAmount(400).build()
					)
			);
		};
	}

}
//orderItems(Arrays.asList(new OderItem(), new OderItem()))