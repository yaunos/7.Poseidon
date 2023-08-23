package com.nnk.springboot.configuration;

import com.nnk.springboot.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// Indique à SpringBoot qu'il s'agit d'une classe de config
@Configuration

// Permet à SpringSecurity de savoir où se trouve la config web
@EnableWebSecurity

// Etendre cette classe avec WebSecurityConfigurer Adapter permet de gérer la chaîne de filtres
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
//public class SpringSecurityConfig {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    // On override la sécurité de Spring Security avec une configuration personnalisée de type HttpSecurity
    @Override
    public void configure(HttpSecurity http) throws Exception {

    // On configure SecurityFilterChain au lieu de Override
    //@Bean
    //public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Mise en place de la chaîne de filtres http

        http
                // Désactive la protection CSRF -> désactivation pas recommandée, ligne commentée pour le moment
                // .csrf().disable()

                .authorizeRequests()
                // toutes les requêtes sur le serveur doivent être authetifiées
                .anyRequest().authenticated()
                //.antMatchers("/user/list", "/user/add", "/user/update").hasRole("ADMIN")
                .antMatchers("/user/list", "/user/add", "/user/update").hasAuthority("ADMIN")
                //.antMatchers("/user/list", "/user/add", "/user/update").hasRole("ADMIN")
                .and()
                .formLogin()
                //.oauth2Login()
                //.loginPage("/login")
                //.defaultSuccessUrl("/loginSuccess.html", true)
                //.failureUrl("/loginFail.html")
                .permitAll()
                .and()
                //.permitAll()
                .logout();

    }

    // Mise en place de la méthode d'identification qui doit pouvoir prendre en compte un Active Directory, des identifiants de base de données
    // Utilisation de AuthentificationManagerBuilder
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

        //auth.userDetailsService(username -> (UserDetails) userDetailsService);

        authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder());

        // on ajoute des utilisateurs pour tester l'API et s'affranchir des utilisateurs de la base de données

        authenticationManagerBuilder.inMemoryAuthentication()
                //.withUser("user").password("easypassword").roles("USER")
                .withUser("user").password("$2y$10$8D/y9DQyoLIucGiLuySDJuxhLAq7.1vy/UjZGobILX.h9/Uhh8bOy").roles("USER")
                .and()
                //.withUser("poseidon").password("strongpassword").roles("ADMIN");
                .withUser("poseidon").password("$2y$10$iXN.lAOsHgJry2XyZazFrunXrP0OnfAfLbI8eSTEAYlODB8I19jLK").roles("USER");
    }

    /**
     * On ajoute une chose à ces mots de passes dans l'application pour être la plus sécurisée possible
     *  : l'encryption des mots des passe avec l'algoritme de hashage bCrypt
     */

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
