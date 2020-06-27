package ru.netology.testcase.domain.services;

import ru.netology.testcase.domain.dto.OriginalUrl;
import ru.netology.testcase.domain.dto.ShortUrl;

public interface ShortenUrlService {
    ShortUrl shortenUrl(OriginalUrl originalUrl);

    OriginalUrl getOriginal(ShortUrl shortUrl);
}
