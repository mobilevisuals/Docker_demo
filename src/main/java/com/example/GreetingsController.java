package com.example;

import javax.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/greeting")
public class GreetingsController {

    @GetMapping(value = {"{account}/type/{type}/id/{id}", "{account}/type/{type}", "{account}/id/{id}"})
    public String handleGreeting(@PathVariable(required = true) String account, @PathVariable(required = false) String type,
            @PathVariable(required = false) @Min(0) Integer id) {
        switch (account) {
            case "personal":
                return "Hi, userId " + id;
            case "business":
                if (type == null) {
                    throw new GreetingException("You have to choose a type if you have a business account.");
                } else {
                    switch (type) {

                        case "big":
                            return "Welcome, business user! ";
                        case "small":
                            throw new GreetingException("Path is not yet implemented");
                        default:
                            throw new GreetingException("Allowed types are only small and big.");
                    }
                }
            default:
                throw new GreetingException("Allowed accounts are only business and personal.");
        }

    }
}
