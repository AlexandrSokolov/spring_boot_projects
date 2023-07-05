package com.example.di.ann.lib;

import com.example.di.ann.lib.api.LibServiceApi1;
import com.example.di.ann.lib.api.LibServiceApi2;
import com.example.di.ann.lib.service.LibServiceByConstructor;
import com.example.di.ann.lib.service.LibServiceByFactoryMethod;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example.di.ann.lib")
public class AnnotationLibraryConfiguration {

//  @Bean
//  public LibServiceApi1 libServiceByConstructor() {
//    return new LibServiceByConstructor();
//  }
//
//  @Bean
//  public LibServiceApi2 serviceApi2() {
//    return LibServiceByFactoryMethod.instance();
//  }
}
