package com.pastebin.url.controller;

import com.pastebin.url.dto.Request;
import com.pastebin.url.dto.Response;
import com.pastebin.url.service.url.UrlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Valid
@RestController
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @GetMapping("/{hash}")
    public RedirectView getRedirectView(@PathVariable("hash") String hash) {
        return urlService.getRedirectView(hash);
    }

    @PostMapping("/url")
    public Response createShortUrl(@RequestBody Request dto) {
        return urlService.createShortUrl(dto);
    }
}