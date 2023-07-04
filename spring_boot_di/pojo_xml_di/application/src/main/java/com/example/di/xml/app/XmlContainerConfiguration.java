package com.example.di.xml.app;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource( {"classpath:beans.xml", "classpath:library-beans.xml"})
public class XmlContainerConfiguration {
}
