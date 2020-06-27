package ru.netology.testcase.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.testcase.domain.dto.OriginalUrl;
import ru.netology.testcase.domain.dto.ShortUrl;
import ru.netology.testcase.domain.services.ShortenUrlService;

@RestController
@RequiredArgsConstructor
public class UrlShortenerController {
    private final ShortenUrlService shortenUrlService;

    @GetMapping("/short")
    public ShortUrl shortenUrl(@RequestParam("url") OriginalUrl originalUrl) {
        return shortenUrlService.shortenUrl(originalUrl);
    }

    @GetMapping("/{original}")
    public OriginalUrl getAll(@PathVariable("original") ShortUrl shortUrl) {
        return shortenUrlService.getOriginal(shortUrl);
    }
}
