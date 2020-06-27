package ru.netology.testcase.domain.services;

import ru.netology.testcase.domain.dto.ShortUrl;

public interface UrlConverterService {
    ShortUrl shorten(Long id);

    Long unshorten(ShortUrl url);
}
