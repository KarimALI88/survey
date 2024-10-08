//package com.example.demo.config;
//
//import com.google.common.base.Predicate;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import static com.google.common.base.Predicates.or;
//import static springfox.documentation.builders.PathSelectors.regex;
//
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//
//    @Bean
//    public Docket postsApi() {
//        return new Docket(DocumentationType.SWAGGER_2).groupName("private-api")
//                .apiInfo(apiInfo()).select().paths(postPaths()).build();
//    }
//
//    private Predicate<String> postPaths() {
//        return or(regex("/api.*"), or(regex("/api/.*")));
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder().title("")
//                .description("SOW FOR SURVEY SYSTEM API reference for developers")
//                .version("1.0")  .termsOfServiceUrl("")
//                .contact("ahmedabdelgawad951@gmail.com").license("")
//                .licenseUrl("").version("1.0").build();
//    }
//
//}
