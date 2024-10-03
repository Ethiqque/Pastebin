package com.pastebin.controller;

import com.pastebin.model.dto.url.Request;
import com.pastebin.model.dto.url.Response;
import com.pastebin.service.url.UrlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Valid
@RestController
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @GetMapping("r/{hash}")
    public RedirectView getRedirectView(@PathVariable("hash") String hash) {
        return urlService.getRedirectView(hash);
    }

    @GetMapping("/{hash}")
    public String getUrl(@PathVariable("hash") String hash) {
        return urlService.getUrl(hash);
    }

    @PostMapping("/url")
    public Response createShortUrl(@RequestBody Request dto) {
        return urlService.createShortUrl(dto);
    }
}