package it.uniroma3.siw.autenticazione;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurazioneCloudinary {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "pickee-server",
                "api_key", "932777766998682",
                "api_secret", "Jk66Yut_CGrTHkSqYvvRzxaq5oA"));
    }
}
