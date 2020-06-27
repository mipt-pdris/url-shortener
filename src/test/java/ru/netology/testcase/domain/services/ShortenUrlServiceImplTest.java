package ru.netology.testcase.domain.services;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.netology.testcase.domain.dto.OriginalUrl;
import ru.netology.testcase.domain.dto.ShortUrl;
import ru.netology.testcase.model.dao.UrlRepository;
import ru.netology.testcase.model.entities.UrlEntity;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShortenUrlServiceImplTest {
    @Mock
    UrlRepository urlRepository;
    @Mock
    UrlConverterService urlConverterService;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    ShortenUrlService shortenUrlService;

    @Before
    public void setUp() {
        shortenUrlService = new ShortenUrlServiceImpl(urlRepository, urlConverterService);
    }

    @Test
    public void shortenUrl() {
        String url = "google.com";
        String expectedUrl = url + "/" + 1;
        ShortUrl expected = new ShortUrl(expectedUrl);
        long id = 1L;

        when(urlConverterService.shorten(eq(id))).thenReturn(expected);
        when(urlRepository.save(any(UrlEntity.class))).thenReturn(new UrlEntity(id));
        ShortUrl actual = shortenUrlService.shortenUrl(new OriginalUrl(url));
        verify(urlRepository).save(new UrlEntity(url));
        assertEquals(expected, actual);
    }

    @Test
    public void shortenPresavedUrl() {
        String url = "google.com";
        String expectedUrl = url + "/" + 1;
        ShortUrl expected = new ShortUrl(expectedUrl);
        long id = 1L;

        when(urlConverterService.shorten(eq(id))).thenReturn(expected);
        when(urlRepository.findByOriginalUrl(eq(url))).thenReturn(Optional.of(new UrlEntity(id)));
        ShortUrl actual = shortenUrlService.shortenUrl(new OriginalUrl(url));
        verify(urlRepository).findByOriginalUrl(url);
        verify(urlConverterService).shorten(id);
        assertEquals(expected, actual);
    }

    @Test
    public void getOriginal() {
        long id = 1L;
        String url = "google.com";
        ShortUrl shortUrl = new ShortUrl("1");

        when(urlRepository.findById(eq(id))).thenReturn(Optional.of(new UrlEntity(url)));
        when(urlConverterService.unshorten(eq(shortUrl))).thenReturn(id);
        OriginalUrl actual = shortenUrlService.getOriginal(shortUrl);
        assertEquals(new OriginalUrl(url), actual);
    }

    @Test
    public void getOriginalWithException() {
        expectedException.expectMessage("Can't find original url by shortened url");
        expectedException.expect(IllegalArgumentException.class);

        when(urlRepository.findById(any())).thenReturn(Optional.empty());
        shortenUrlService.getOriginal(new ShortUrl(""));
    }
}