package ru.netology.testcase.utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;

@UtilityClass
public class ServletUtil {
    @SneakyThrows
    public static String getUrlHost() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest servletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
        URL requestURL = new URL(servletRequest.getRequestURL().toString());
        return requestURL.getAuthority();
    }
}
