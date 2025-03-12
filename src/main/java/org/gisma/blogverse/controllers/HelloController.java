package org.gisma.blogverse.controllers;

import org.gisma.blogverse.models.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Message hello() {
        return new Message("Server is up and running!");
    }
}
