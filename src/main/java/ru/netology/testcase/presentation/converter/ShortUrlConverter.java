package ru.netology.testcase.presentation.converter;

import org.springframework.core.convert.converter.Converter;
import ru.netology.testcase.domain.dto.ShortUrl;

public class ShortUrlConverter implements Converter<String, ShortUrl> {
    @Override
    public ShortUrl convert(String source) {
        return new ShortUrl(source);
    }
}
