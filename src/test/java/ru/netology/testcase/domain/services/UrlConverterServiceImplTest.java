package ru.netology.testcase.domain.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.netology.testcase.domain.dto.ShortUrl;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UrlConverterServiceImplTest {
    @Mock
    HttpServletRequest request;
    UrlConverterService urlConverterService = new UrlConverterServiceImpl();
    private String url = "google.com:9080";
    private String host = "http://" + url;

    @Before
    public void setUp() {
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(request.getRequestURL()).thenReturn(new StringBuffer(host));
    }

    @Test
    public void shorten() {
        Long id = 1L;
        ShortUrl actual = urlConverterService.shorten(id);
        assertEquals(new ShortUrl(url + "/" + id), actual);
    }

    @Test
    public void unshorten() {
        Long id = 1L;
        assertEquals(urlConverterService.unshorten(new ShortUrl("1")), id);
    }
}