package com.inf1r.pract2.Controllers;

import com.inf1r.pract2.Models.Role;
import com.inf1r.pract2.Models.User;
import com.inf1r.pract2.repo.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepos userRepos;

    @GetMapping("/registration")
    public String reg(){
        return "REGandLOG/reg";
    }
    @GetMapping("/login")
    public String log() {
        return "REGandLOG/login";
    }
    @PostMapping("/registration")
    public String addUser(User user, Model model){
        User userFromDb = userRepos.findByUsername(user.getUsername());
        if(userFromDb != null){
            model.addAttribute("message", "Пользователь с таким логином уже есть");
            return "REGandLOG/reg";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepos.save(user);
        return "redirect:/login";
    }
}
