package web.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.entity.User;
import web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/")
public class MyController {
    private final UserService userService;

    @Autowired
    public MyController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showAllUsers(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "all-users";
    }

    @GetMapping("/addNewUser")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        return "user-info";
    }

    @PostMapping()
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam(value = "id",required = false) long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String updateUser(@RequestParam(value = "id",required = false) long id, ModelMap modelMap) {
        User user = userService.getUser(id);
        modelMap.addAttribute("user",user);
        return "user-info";
    }

}
