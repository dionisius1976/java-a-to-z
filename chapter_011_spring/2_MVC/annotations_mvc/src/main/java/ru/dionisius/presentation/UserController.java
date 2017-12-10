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
public class UserController {
//    private List<Ad> ads = null;
//    private handler
//    @RequestMapping(value = "/users", method = RequestMethod.GET)

    @Autowired
//    @Qualifier(value = "AdService")
    IAdService adService;

    //    @Autowired
//    @Qualifier(value = "FakeDAO")
//    public void setAdService(IAdService adService) {
//        this.adService = adService;
//    }

    @RequestMapping(value = "/ad", method = RequestMethod.GET)
    public @ResponseBody String showHi(final ModelMap modelMap) {
//        modelMap.addAttribute("ads", this.adService.getAll());
        return "ads";
    }

//    @GetMapping
    @RequestMapping(value = "/ads", method = RequestMethod.GET)
    public String showAds(final ModelMap modelMap) {
        modelMap.addAttribute("ads", this.adService.getAll());
        return "ads";
    }

//    @PostMapping
//    public String addUser(@ModelAttribute Ad ad) {
//        this.ads.add(ad);
//        return "redirect:users.do";
//    }

}
