package com.app.telegrambot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new ConverterFactory<String, Enum>() {

            @SuppressWarnings("unchecked")
            @Override
            public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
                return source -> {
                    try {
                        return (T) Enum.valueOf(targetType, source);
                    } catch (Exception e) {
                        return targetType.getEnumConstants()[Integer.parseInt(source)];
                    }
                };
            }
        });
    }
}
