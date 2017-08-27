package com.marcuseisele.linkshortener.service;

import com.marcuseisele.linkshortener.Exception.LinkNotFoundException;
import com.marcuseisele.linkshortener.api.dto.Link;
import com.marcuseisele.linkshortener.repository.LinkRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class LinkService {

    private final LinkRepository linkRepository;

    LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public Link getLink(String shortLink) throws LinkNotFoundException {
        return linkRepository
                .findByShortLink(shortLink)
                .orElseThrow(() -> new LinkNotFoundException(shortLink));
    }


    public Link createNewLink(Link link) {
        log.info("Creating new link {}", link);
        link.setShortLink(UUID.randomUUID().toString());
        return linkRepository.save(link);
    }
}
