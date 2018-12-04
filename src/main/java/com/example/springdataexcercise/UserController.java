package com.example.springdataexcercise;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String home(Model model,
                       @RequestParam(required = false, defaultValue = "ALL") Action action) {

        List<User> users = new ArrayList<>();
        switch (action) {
            case ALL:
                users = userRepository.findAll();
                break;
            case ALL_JPQL:
                users = userRepository.findAllJPQL();
                break;
            case ALL_SQL:
                users = userRepository.findAllSQL();
                break;
            case CONTAINS_O:
                users = userRepository.findAllByFirstNameIsContainingAndLastNameIsContaining("o", "o");
                break;
            case CONTAINS_O_JPQL:
                users = userRepository.findAllThatHaveOJPQL("o");
                break;
            case CONTAINS_O_SQL:
                users = userRepository.findAllThatHave("o");
                break;
            case DELETE_K:
                userRepository.deleteAllByFirstNameStartingWithIgnoreCase("k");
                users = userRepository.findAll();
                break;
            case DELETE_K_JPQL:
                userRepository.deleteAllByFirstNameStartingWithJPQL("K");
                users = userRepository.findAll();
                break;
            case DELETE_K_SQL:
                userRepository.deleteAllByFirstNameStartingWithSQL("K");
                users = userRepository.findAll();
                break;
            case ORDER:
                users = userRepository.findAllByOrderByLastName();
                break;
            case ORDER_JPQL:
                break;
            case ORDER_SQL:
                break;
        }

        model.addAttribute("users", users);

        return "home";
    }
}