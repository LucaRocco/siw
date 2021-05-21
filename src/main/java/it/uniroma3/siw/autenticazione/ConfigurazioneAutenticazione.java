package it.uniroma3.siw.autenticazione;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import static it.uniroma3.siw.utility.Costanti.AMMINISTRATORE;

@Configuration
@EnableWebSecurity
public class ConfigurazioneAutenticazione extends WebSecurityConfigurerAdapter {

    private final DataSource datasource;

    public ConfigurazioneAutenticazione(DataSource dataSource) {
        this.datasource = dataSource;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/**").permitAll()
                .antMatchers(HttpMethod.GET, "/admin/**").hasAnyAuthority(AMMINISTRATORE)
                .anyRequest().authenticated()
                .and().formLogin()
                .defaultSuccessUrl("/home")
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/index")
                .invalidateHttpSession(true)
                .clearAuthentication(true).permitAll();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(this.datasource)
                .authoritiesByUsernameQuery("SELECT email, ruolo FROM amministratore WHERE email=?")
                .usersByUsernameQuery("SELECT email, password, 1 as enabled FROM amministratore WHERE email=?");
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}