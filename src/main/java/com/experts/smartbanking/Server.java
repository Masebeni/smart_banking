package com.experts.smartbanking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by AXE on 2016/08/20.
 */
@Configuration
@ComponentScan
@EnableWebMvc
@EnableAutoConfiguration
public class Server {
    public static void main(String[] args){

        System.out.println( "Starting up server.." );
        SpringApplication.run(Server.class, args);
    }
}
