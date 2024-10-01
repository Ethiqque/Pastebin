package com.pastebin.url.service.url;

import com.pastebin.url.dto.Request;
import com.pastebin.url.dto.Response;
import org.springframework.web.servlet.view.RedirectView;

public interface UrlService {

    RedirectView getRedirectView(String hash);

    Response createShortUrl(Request dto);
}
