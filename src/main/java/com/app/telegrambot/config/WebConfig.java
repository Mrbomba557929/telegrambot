package com.app.telegrambot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new ConverterFactory<String, Enum>() {

            @SuppressWarnings("unchecked")
            @Override
            public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
                return source -> {
                    log.error("Я тут");
                    try {
                        return (T) Enum.valueOf(targetType, source.toUpperCase());
                    } catch (Exception e) {
                        return targetType.getEnumConstants()[Integer.parseInt(source.toUpperCase())];
                    }
                };
            }
        });
    }
}
