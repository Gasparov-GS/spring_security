package web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String userPage(Principal principal, ModelMap model) {
        List<String> messages = new ArrayList<>();
        User user = userService.findUserByName(principal.getName())
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));
        messages.add("Name: " + user.getName());
        messages.add("ID: " + user.getId());
        messages.add("E-mail: " + user.getMail());
        model.addAttribute("messages", messages);
        return "hello";
    }
}