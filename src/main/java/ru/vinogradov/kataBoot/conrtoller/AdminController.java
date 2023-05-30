package ru.vinogradov.kataBoot.conrtoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.vinogradov.kataBoot.model.Role;
import ru.vinogradov.kataBoot.model.User;
import ru.vinogradov.kataBoot.service.RoleService;
import ru.vinogradov.kataBoot.service.UserService;



import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping
public class AdminController {
    private UserService userService;

    private RoleService roleService;

    public AdminController() {}

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping("/ad")
    public String getUsers(@ModelAttribute("user") User user, Model model, Principal principal) {
        model.addAttribute("admin", this.userService.findByEmail(principal.getName()));
        return "index";
    }

    @PostMapping("/ad")
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "rolesId") Long rolesId) {
        if (rolesId == 2) {
            user.setRoles(Collections.singleton(roleService.getRole(2L)));
        } else if (rolesId == 1){
            user.setRoles(Collections.singleton(roleService.getRole(1L)));
        }else {
            user.setRoles(roleService.getListRoles());
        }
        userService.saveUser(user);
        return "redirect:/ad";
    }
}
