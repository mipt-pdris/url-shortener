package ru.netology.testcase.presentation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.netology.testcase.presentation.converter.OriginalUrlConverter;
import ru.netology.testcase.presentation.converter.ShortUrlConverter;

@Configuration
public class UrlMvcConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new OriginalUrlConverter());
        registry.addConverter(new ShortUrlConverter());
    }
}
