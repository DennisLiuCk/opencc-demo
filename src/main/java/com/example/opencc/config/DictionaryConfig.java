package com.example.opencc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:dictionary.properties", encoding = "UTF-8")
public class DictionaryConfig {
} 