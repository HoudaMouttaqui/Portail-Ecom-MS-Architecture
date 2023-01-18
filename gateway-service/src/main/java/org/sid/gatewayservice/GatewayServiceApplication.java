package org.sid.gatewayservice;

import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.net.http.HttpClient;

@SpringBootApplication
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}
@Bean
	public RouteLocator routes(RouteLocatorBuilder builder){
		return builder.routes()
				.route(r->r.path("/customers/**").uri("http://localhost:8081/"))
				.route(r->r.path("/products/**").uri("http://localhost:8082/"))
				.build();
}

}
