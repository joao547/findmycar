package br.edu.ifpe.tads.findmycar.infra.security;

import br.edu.ifpe.tads.findmycar.entity.Usuario;
import br.edu.ifpe.tads.findmycar.repository.UsuarioRepository;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Optional;


@Configuration
@EnableWebMvc
public class WebSecurityConfig {
    private final UsuarioRepository usuarioRepository;
    private final JWTUtil jwtUtil;
    private final AuthenticationConfiguration authenticationConfiguration;

    public WebSecurityConfig(UsuarioRepository usuarioRepository, JWTUtil jwtUtil, AuthenticationConfiguration authenticationConfiguration) {
        this.usuarioRepository = usuarioRepository;
        this.jwtUtil = jwtUtil;
        this.authenticationConfiguration = authenticationConfiguration;
    }



    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration configAutenticacao = new CorsConfiguration();
        configAutenticacao.setAllowCredentials(true);
        configAutenticacao.addAllowedOriginPattern("**");
        configAutenticacao.addAllowedHeader("Authorization");
        configAutenticacao.addAllowedHeader("Content-Type");
        configAutenticacao.addAllowedHeader("Accept");
        configAutenticacao.addAllowedMethod("POST");
        configAutenticacao.addAllowedMethod("GET");
        configAutenticacao.addAllowedMethod("DELETE");
        configAutenticacao.addAllowedMethod("PUT");
        configAutenticacao.addAllowedMethod("OPTIONS");
        configAutenticacao.setMaxAge(3600L);
        source.registerCorsConfiguration("/**", configAutenticacao); // Global para todas as URLs da aplicação

        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests((authorize) ->
                authorize.anyRequest().permitAll()
            );

        http.addFilter(new JWTAuthorizationFilter(authenticationConfiguration.getAuthenticationManager(), jwtUtil, userDetailsService()));

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager (
            UserDetailsService userDetailsService,
            PasswordEncoder pa
    ) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(pa);
        authenticationProvider.setUserDetailsService(userDetailsService());

        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Optional<Usuario> usuarioOptional = this.usuarioRepository.findByEmail(username);
            Usuario usuario = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("User not found with email " + username));

            System.out.printf("ACHOU ESSE FILHA DA PUITA %s%n", usuario.toString());

            return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getSenha())
                .roles("CLIENT")
                .build();
        };
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
