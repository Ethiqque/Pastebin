package com.pastebin.url.scheduler;

import com.pastebin.url.entity.Url;
import com.pastebin.url.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CleanerScheduler {

    private final UrlRepository urlRepository;

    @Scheduled(cron = "${scheduler.clear.cron}")
    public void deleteOldUrls() {
        List<Url> urls = urlRepository.findOldUrls();

        if (!urls.isEmpty()) {
            urlRepository.deleteAll(urls);
            log.info("Deleted {} old URLs", urls.size());
        }
    }
}
