package com.AkashSharma.AkashAssignment3.Controller;

import com.AkashSharma.AkashAssignment3.Pojo.ToyPojo;
import com.AkashSharma.AkashAssignment3.Service.ToyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class ToyController {
    @Autowired
    private ToyService toyService;

    @GetMapping("/")
    public String retrieveHP(HttpSession session, Model model) {
        return toyService.retrieveHP(session, model);
    }

    @GetMapping("/retrieveCart")
    public String retrieveCart(HttpSession session, Model model){
        return toyService.retrieveCart(session, model);
    }

    @PostMapping("/setItemToCart")
    public String setItemToCart(@RequestParam("id") Long id, HttpSession session){
        return toyService.setItemToCart(id, session);
    }

    @PostMapping("/removeItemFromCart")
    public String removeItemFromCart(@RequestParam("id") Long id, HttpSession session){
        return toyService.removeItemFromCart(id, session);
    }

    @PostMapping("/deleteSession")
    public String deleteSession(HttpSession session) {
        return toyService.deleteSession(session);
    }

    @GetMapping("/retrieveAdminPage")
    public String retrieveAdminPage(Model model){
        return toyService.retrieveAdminPage(model);
    }

    @PostMapping("/setToy")
    public String setToy(@ModelAttribute("toy") ToyPojo toy){
        return toyService.setToy(toy);
    }

    @PostMapping("/removeToyById")
    public String removeToyById(@RequestParam("id") Long id,HttpSession session){
        return toyService.removeToyById(id, session);
    }

    @PostMapping("/retrieveUpdateItemPage")
    public String retrieveUpdateItemPage(@RequestParam("id") Long id,Model model){
        return toyService.retrieveUpdateItemPage(id, model);
    }

    @PostMapping("/updateToy")
    public String updateToy(@RequestParam("id")Long id, @ModelAttribute("toy") ToyPojo toy){
        return toyService.updateToy(id, toy);
    }
}
