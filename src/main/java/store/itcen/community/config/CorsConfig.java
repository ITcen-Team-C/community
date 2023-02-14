package store.itcen.community.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // api cors 정책 설정
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080", "http://52.79.240.53") // api요청 허용 URL
                .allowedMethods("GET", "POST","PATCH","PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}