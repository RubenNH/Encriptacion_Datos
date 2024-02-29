package stay.cruz.cruzstay.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import stay.cruz.cruzstay.security.Service.UserDetailsServiceImpl;
import stay.cruz.cruzstay.security.jwt.JwtAuthenticationFilter;


/*
 Este es el archivo de configuración de seguridad principal de la aplicación.
    Se encarga de configurar la seguridad de la aplicación, permitiendo el acceso a ciertas rutas sin autenticación y
    requiriendo autenticación para otras rutas.
    Cuando un usuario intenta acceder a una ruta que requiere autenticación, el usuario debe enviar un token de autenticación
    de acuerdo al token se puede determinar si el usuario tiene permisos para acceder a la ruta.
    Si el token es válido, el usuario puede acceder a la ruta, de lo contrario, se le niega el acceso.
    La eception a lo de arriba son las rutas que se encuentran en la lista blanca, estas rutas no requieren autenticación.

    Esti archivo también se encarga de configurar el filtro de autenticación de JWT, el cual se encarga de verificar si el token
    enviado por el usuario es válido.

    Abajo se muestran ejemplos comentados de cómo se puede configurar la seguridad de la aplicación.
*/
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class MainSecurity {
    private final String[] WHITE_LIST = {
            "/api/category/**",
            "/api/auth/**"
    };

    private final UserDetailsServiceImpl service;

    public MainSecurity(UserDetailsServiceImpl service) {
        this.service = service;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter filter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setUserDetailsService(service);
        dao.setPasswordEncoder(passwordEncoder());
        return dao;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults()).csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(WHITE_LIST).permitAll()
                                //req.requestMatchers(HttpMethod.GET, WHITE_LIST).permitAll()
                                //req.requestMatchers(WHITE_LIST).hasAnyAuthority("ADMIN_ROLE", "USER_ROLE")
                                //.requestMatchers("/api/user/**").hasAuthority("ADMIN_ROLE")
                                //.requestMatchers("/api/person/**").hasAnyAuthority("ADMIN_ROLE", "USER_ROLE")
                                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(filter(), UsernamePasswordAuthenticationFilter.class)
                .logout(out -> out.logoutUrl("/api/auth/logout").clearAuthentication(true));
        return http.build();
    }

}
