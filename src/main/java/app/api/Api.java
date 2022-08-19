package app.api;

import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)

public class Api {

	@Bean
	@RouterOperation(beanClass = ApiHandler.class, beanMethod = "hello")
	public RouterFunction<ServerResponse> route(ApiHandler apiHandler) {
		return RouterFunctions.route(GET("/hello").and(accept(MediaType.APPLICATION_JSON)), apiHandler::hello)
				.andRoute(GET("/").and(accept(MediaType.APPLICATION_JSON)), apiHandler::hello)
				;
	}
}