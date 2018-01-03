package ru.dionisius.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.dionisius.data.models.Ad;
import ru.dionisius.service.AdService;
import ru.dionisius.service.IAdService;

import java.util.List;

/**
 * Created by Dionisius on 26.10.2017.
 */
@Controller
@RequestMapping("/")
public class UserController {

    public UserController() {
        System.out.println("UserController instantiated.");
    }

    @Autowired
    IAdService adService;

    @GetMapping
    public String showAds(final ModelMap modelMap) {
        List<Ad> ads = (List<Ad>)this.adService.getAll();
        modelMap.addAttribute("ads", ads);
        return "ads";
    }

//    @PostMapping
//    public String addUser(@ModelAttribute Ad ad) {
//        this.ads.add(ad);
//        return "redirect:users.do";
//    }

}
