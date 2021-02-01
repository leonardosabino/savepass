package br.com.savepass.savepass.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
public class SwaggerConfiguration {

    private static final String SWAGGER_API_VERSION = "1.0.0";
    private static final String TITLE = "SavePass";
    private static final String DESC = "Management keys services";

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(TITLE)
                .description(DESC)
                .contact(new Contact("Leonardo Sabino", "http://www.github.com/leonardosabino",
                        "http://www.github.com/leonardosabino"))
                .version(SWAGGER_API_VERSION)
                .build();
    }

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathMapping("/")
                .select()
                .paths(PathSelectors.regex("/v1.*"))
                .build();
    }

}
