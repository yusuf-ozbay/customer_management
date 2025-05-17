package org.example.customer_management.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    //Swaggerı çalıştımak için tarayıcıya şunu ypıştırın:   http://localhost:8080/swagger-ui/index.html#

    @Bean
    public OpenAPI customOpenAPI() {   //API'nin dökümantasyonunu oluşturmak için kullanılır.
        return new OpenAPI()
                .info(new Info()   //API'nin ne hakkında olduğunu, hangi versiyonun kullanıldığını, iletişim bilgilerini gibi bilgileri barındırır.
                        .title("Customer Management API")
                        .description("Müşteri yönetimi için geliştirilmiş bir API")
                        .version("1.0")
                        .contact(new Contact()  //API'nin sahibi ya da geliştiricisiyle ilgili iletişim bilgilerini içerir.
                                .name("Yusuf Özbay")
                                .email("ozbay9985@gmail.com")
                                .url("https://github.com/yusuf-ozbay")
                        )
                );
    }
}
