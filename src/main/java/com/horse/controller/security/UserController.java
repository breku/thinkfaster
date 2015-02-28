package com.horse.controller.security;

import com.horse.controller.AbstractController;
import com.horse.model.User;
import com.horse.service.database.security.UserService;
import com.horse.utils.security.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * User: Breku
 * Date: 2014-09-21
 */
@Controller
@RequestMapping(value = "/admin")
public class UserController extends AbstractController {


    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/users/list")
    @Override
    public ModelAndView initView(Model model) {
        List<User> userList = userService.getAll();
        model.addAttribute("userList", userList);
        model.addAttribute("allAuthorities", RoleName.values());
        return new ModelAndView("security/users/userList", model.asMap());
    }

    @RequestMapping(value = "/users/addRole/{userId}/{authority}", method = RequestMethod.POST)
    public String addAuthority(@PathVariable("userId") long userId, @PathVariable("authority") RoleName roleName) {
        User user = userService.get(userId);
        userService.grantAuthorityToUser(user, roleName);
        return "redirect:/users/list";
    }

    @RequestMapping(value = "/users/removeRole/{userId}/{authority}", method = RequestMethod.POST)
    public String removeAuthority(@PathVariable("userId") long userId, @PathVariable("authority") RoleName roleName) {
        User user = userService.get(userId);
        userService.removeAuthorityFromUser(user, roleName);
        return "redirect:/users/list";
    }

}
