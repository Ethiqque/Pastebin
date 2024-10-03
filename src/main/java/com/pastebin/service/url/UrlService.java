package com.pastebin.service.url;

import com.pastebin.model.dto.url.Request;
import com.pastebin.model.dto.url.Response;
import org.springframework.web.servlet.view.RedirectView;

public interface UrlService {

    RedirectView getRedirectView(String hash);

    Response createShortUrl(Request dto);

    String getUrl(String hash);
}
