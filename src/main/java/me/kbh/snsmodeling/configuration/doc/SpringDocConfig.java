package me.kbh.snsmodeling.configuration.doc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

  @Bean
  OpenAPI openAPI() {
    Contact contact = new Contact().name("API Support").email("elasticsearch@kakao.com");

    Info info =
        new Info()
            .title("elasticsearch api")
            .version("v0.0.1")
            .contact(contact)
            .description("소상공인시장진흥공단에서 제공하는 공공데이터인 서울 상가 정보를 활용하여 ELK 스택을 활용한 프로젝트");

    return new OpenAPI().info(info);
  }
}
