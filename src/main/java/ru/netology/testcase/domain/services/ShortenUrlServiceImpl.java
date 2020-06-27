package ru.netology.testcase.domain.services;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.netology.testcase.model.dao.UrlRepository;
import ru.netology.testcase.model.entities.UrlEntity;
import ru.netology.testcase.domain.dto.OriginalUrl;
import ru.netology.testcase.domain.dto.ShortUrl;

import javax.servlet.http.HttpServletRequest;

import java.net.URL;
import java.util.Optional;

import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ShortenUrlServiceImpl implements ShortenUrlService {
    UrlRepository urlRepository;
    UrlConverterService urlConverterService;

    @Override
    public ShortUrl shortenUrl(OriginalUrl originalUrl) {
        Optional<UrlEntity> byOriginalUrl = urlRepository.findByOriginalUrl(originalUrl.getUrl());
        UrlEntity urlEntity = byOriginalUrl
                .orElseGet(() -> urlRepository.save(new UrlEntity(originalUrl.getUrl())));
        return urlConverterService.shorten(urlEntity.getId());
    }

    @Override
    public OriginalUrl getOriginal(ShortUrl shortUrl) {
        Long id = urlConverterService.unshorten(shortUrl);
        String originalUrl = urlRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(format("Can't find original url by shortened url %s", shortUrl.getShortUrl())))
                .getOriginalUrl();
        return new OriginalUrl(originalUrl);
    }
}
