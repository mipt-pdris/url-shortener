package ru.netology.testcase.model.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.netology.testcase.model.entities.UrlEntity;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UrlRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UrlRepository urlRepository;

    @Test
    public void findByOriginalUrl() {
        String originalUrl = "www.google.com";
        UrlEntity persist = entityManager.persist(new UrlEntity(originalUrl));
        entityManager.flush();

        Optional<UrlEntity> byOriginalUrl = urlRepository.findByOriginalUrl(originalUrl);
        UrlEntity actual = byOriginalUrl.get();
        assertEquals(persist, actual);
    }
}