package ru.netology.testcase.domain.services;

import org.springframework.stereotype.Service;
import ru.netology.testcase.domain.dto.ShortUrl;

import static ru.netology.testcase.utils.ServletUtil.getUrlHost;

@Service
public class UrlConverterServiceImpl implements UrlConverterService {

    @Override
    public ShortUrl shorten(Long id) {
        String host = getUrlHost();
        return new ShortUrl(host + "/" + Long.toHexString(id));
    }

    @Override
    public Long unshorten(ShortUrl url) {
        return Long.decode(url.getShortUrl());
    }
}
