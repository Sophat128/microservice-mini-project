//package com.example;
//
//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Contact;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.servers.Server;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@OpenAPIDefinition
//@Component
//public class OpenAPIConfiguration {
//
//        @Bean
//        public OpenAPI customOpenAPI(
//                @Value("${openapi.service.title}") String serviceTitle,
//                @Value("${openapi.service.version}") String serviceVersion,
//                @Value("${openapi.service.url}") String url) {
//            return new OpenAPI()
//                    .servers(List.of(new Server().url(url)))
//                    .info(new Info().title(serviceTitle).version(serviceVersion));
//        }
//
//}
