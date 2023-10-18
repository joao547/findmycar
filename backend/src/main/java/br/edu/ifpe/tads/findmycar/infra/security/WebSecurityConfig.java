package br.edu.ifpe.tads.findmycar.infra.security;

import br.edu.ifpe.tads.findmycar.entity.Usuario;
import br.edu.ifpe.tads.findmycar.repository.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
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
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests((authorize) ->
                authorize.requestMatchers(HttpMethod.POST, "/api/auth/*").permitAll()
                .anyRequest().authenticated()
            );

        http.addFilter(new JWTAuthorizationFilter(authenticationConfiguration.getAuthenticationManager(), jwtUtil, userDetailsService()));
        http.addFilter(new JWTAuthenticationFilter(authenticationConfiguration.getAuthenticationManager(), jwtUtil));

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager (
            UserDetailsService userDetailsService,
            PasswordEncoder pa
    ) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(pa);
        authenticationProvider.setUserDetailsService(userDetailsService);

        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Usuario usuario = this.usuarioRepository.findUsuarioByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email " + username));

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
