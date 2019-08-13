package com.labs.sauti.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

// http://localhost:8080/swagger-ui.html
@Configuration
class Swagger2Config {
    @Bean
    public Docket api()  {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false) // Allows only my exception responses
                .ignoredParameterTypes(Pageable.class) // allows only my paging parameter list
                .apiInfo(apiEndPointsInfo());
    }

    private ApiInfo apiEndPointsInfo()  {
        return new ApiInfoBuilder().title("Sauti")
                .description("Sauti Africa Lambda Labs Backend")
                .contact(new Contact("Israel dela Cruz", "http://www.lambdaschool.com", "igdcruz42@gmail.com"))
                .license("MIT").licenseUrl("https://github.com/LambdaSchool/java-starthere/blob/master/LICENSE")
                .version("1.0.0").build();
    }
}
