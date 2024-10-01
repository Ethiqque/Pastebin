package com.pastebin.scheduler;

import com.pastebin.model.entity.url.Url;
import com.pastebin.repository.url.UrlRepository;
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
