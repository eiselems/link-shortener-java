package com.marcuseisele.linkshortener.api.controller;

import com.marcuseisele.linkshortener.api.dto.Link;
import com.marcuseisele.linkshortener.service.LinkService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@RequestMapping("/links")
public class LinkController {


    private final LinkService linkService;

    LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping("/{shortLink}")
    public void getLink(@PathVariable String shortLink, HttpServletResponse response) throws IOException {
        Link link = linkService.getLink(shortLink);
        response.sendRedirect(link.getTarget());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Link createLink(@RequestBody Link link) {
        return linkService.createNewLink(link);
    }
}
