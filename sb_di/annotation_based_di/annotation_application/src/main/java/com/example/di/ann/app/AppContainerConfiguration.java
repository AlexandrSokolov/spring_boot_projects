package com.example.di.ann.app;


import com.example.di.ann.lib.AnnotationLibraryConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.example.di.ann.app")
@Import(AnnotationLibraryConfiguration.class) //import the library IoC  container configuration
public class AppContainerConfiguration {
}
