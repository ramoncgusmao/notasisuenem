package com.ramon.sisu.config;


import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
          .select()
          .apis(RequestHandlerSelectors.any())
          .paths(PathSelectors.any())
          .build()
          .apiInfo(this.informacoesApi().build())
          .useDefaultResponseMessages(false)
          .globalResponseMessage(RequestMethod.GET, responseMessageForGET())
        	.globalResponseMessage(RequestMethod.POST, responseMessageForPost())
        	 .globalResponseMessage(RequestMethod.PUT, responseMessageForGET())
        	 .globalResponseMessage(RequestMethod.DELETE, responseMessageForGET());
    }
    
    private ApiInfoBuilder informacoesApi() {
    	 
		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
 
		apiInfoBuilder.title("Nota Enem");
		apiInfoBuilder.description("Api para informar dados sobre a media do enem e nota de corte.");
		apiInfoBuilder.version("1.0");
		apiInfoBuilder.termsOfServiceUrl("");
		apiInfoBuilder.license("Licença - Open Source");
		apiInfoBuilder.contact(this.contato());
 
		return apiInfoBuilder;
 
	}
	private Contact contato() {
 
		return new Contact(
				"Ramon Cantanhede Gusmão",
				"https://linkedin.com/in/ramoncantanhede/",
				"ramoncgusmao@gmail.com");
	}
	
	private List<ResponseMessage> responseMessageForGET()
	{
	    return new ArrayList<ResponseMessage>() {{
	        add(new ResponseMessageBuilder()
	            .code(500)
	            .message("Erro interno do servidor")
	            .responseModel(new ModelRef("Error"))
	            .build());
	        add(new ResponseMessageBuilder()
	            .code(403)
	            .message("você não possui permissão para acessar esse conteudo")
	            .build());
	        add(new ResponseMessageBuilder()
		            .code(404)
		            .message("o registro que você procura não existe")
		            .build());
	    }};
	}
	
	private List<ResponseMessage> responseMessageForPost()
	{
	    return new ArrayList<ResponseMessage>() {{
	        add(new ResponseMessageBuilder()
	            .code(500)
	            .message("Erro interno do servidor")
	            .responseModel(new ModelRef("Error"))
	            .build());
	        add(new ResponseMessageBuilder()
	            .code(403)
	            .message("você não possui permissão para acessar esse conteudo")
	            .build());
	        add(new ResponseMessageBuilder()
		            .code(404)
		            .message("o registro que você procura não existe")
		            .build());
	        add(new ResponseMessageBuilder()
		            .code(201)
		            .message("criado com sucesso")
		            .build());
	        
	    }};
	}
}