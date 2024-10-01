package com.pastebin.service.url.url;

import com.pastebin.model.dto.url.Request;
import com.pastebin.model.dto.url.Response;
import com.pastebin.model.entity.url.Url;
import com.pastebin.service.url.cache.HashCache;
import com.pastebin.url.exception.NotFoundException;
import com.pastebin.repository.url.UrlCacheRepository;
import com.pastebin.repository.url.UrlRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {

    private final UrlCacheRepository urlCacheRepository;
    private final UrlRepository urlRepository;
    private final HashCache hashCache;

    @Transactional(readOnly = true)
    public RedirectView getRedirectView(String hash) {
        return new RedirectView(
                urlCacheRepository.getUrlByHash(hash)
                .orElseGet(() -> urlRepository.getUrlByHash(hash).map(Url::getUrl)
                .orElseThrow(() -> new NotFoundException("URL not found for url: " + hash))));
    }

    @Override
    @Transactional
    public Response createShortUrl(Request dto) {
        String hash = hashCache.getHash();

        if (hash == null || hash.isEmpty()) {
            throw new RuntimeException("Failed to generate a url");
        }

        urlRepository.save(new Url(hash, dto.getUrl(), LocalDateTime.now()));
        log.info("Saved url: {}", dto.getUrl());

        urlCacheRepository.saveUrlByHash(dto.getUrl(), hash);
        log.info("Saved url: {} by url: {} in cache", dto.getUrl(), hash);
        return new Response(hash);
    }
}