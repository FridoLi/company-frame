package com.frido.hd.swagger;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${Swagger2.enable}")
    private boolean enable;

    @Bean
    public Docket createRestApi(){
        List<Parameter> pars = new ArrayList<>();
        ParameterBuilder tokenPar = new ParameterBuilder();
        ParameterBuilder refreshTokenPar = new ParameterBuilder();
        tokenPar
                .name("authorization")
                .description("swagger测试用(模拟authorization传入)非必填header")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false);
        refreshTokenPar
                .name("refresh_token")
                .description("swagger测试用(模拟刷新token传入)非必填header")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false);
        pars.add(tokenPar.build());
        pars.add(refreshTokenPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.frido.hd.sys.controller"))
                .build()
                .globalOperationParameters(pars)
                .enable(enable);
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("HD-Monster")
                .description("浩打怪兽")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }
}
