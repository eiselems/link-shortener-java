package com.marcuseisele.linkshortener.repository;

import com.marcuseisele.linkshortener.api.dto.Link;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LinkRepository extends CrudRepository<Link, Long> {
    Optional<Link> findByShortLink(String shortLink);
}
