package it.uniroma3.siw.autenticazione;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

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
                .antMatchers("/admin/**").authenticated()
                .antMatchers("/**").permitAll()
                .and().formLogin()
                .defaultSuccessUrl("/admin/collezioni")
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .clearAuthentication(true).permitAll();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(this.datasource)
                .authoritiesByUsernameQuery("SELECT username, ruolo FROM amministratore WHERE username=?")
                .usersByUsernameQuery("SELECT username, password, 1 as enabled FROM amministratore WHERE username=?");
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}