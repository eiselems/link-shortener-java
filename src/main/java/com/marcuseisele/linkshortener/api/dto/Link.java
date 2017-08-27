package com.marcuseisele.linkshortener.api.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String shortLink;
    private String target;
}
