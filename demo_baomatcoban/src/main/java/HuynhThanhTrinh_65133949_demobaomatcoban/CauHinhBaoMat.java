package HuynhThanhTrinh_65133949_demobaomatcoban;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class CauHinhBaoMat {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/**").permitAll()   // Cho phép không cần login
                .requestMatchers("/products/**").hasRole("USER") // Cần role USER
                .requestMatchers("/admin/**").hasRole("ADMIN")   // Cần role ADMIN
                .anyRequest().authenticated()                  // Các request còn lại cần auth
            )
            .formLogin(form -> form
                .loginPage("/login")          // Tùy chỉnh trang login
                .permitAll()                  // Cho phép ai cũng xem trang login
//                .defaultSuccessUrl("/products", true)
            )
            .logout(logout -> logout
                .permitAll()
                .logoutSuccessUrl("/login?logout")
            );
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password("{noop}123") // {noop} để không mã hóa (chỉ demo)
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password("{noop}123456")
                .roles("ADMIN", "USER")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}