package ru.netology.testcase.presentation.converter;

import org.springframework.core.convert.converter.Converter;
import ru.netology.testcase.domain.dto.OriginalUrl;

public class OriginalUrlConverter implements Converter<String, OriginalUrl> {
    @Override
    public OriginalUrl convert(String source) {
        return new OriginalUrl(source);
    }
}
