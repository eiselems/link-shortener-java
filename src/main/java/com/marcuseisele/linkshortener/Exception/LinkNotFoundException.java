package com.marcuseisele.linkshortener.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class LinkNotFoundException extends RuntimeException{

    public LinkNotFoundException(String id){
        super("Link with id "+ id + " not found");
    }
}
