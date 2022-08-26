package com.AkashSharma.AkashAssignment3.Service;

import com.AkashSharma.AkashAssignment3.Pojo.CartPojo;
import com.AkashSharma.AkashAssignment3.Pojo.ToyPojo;
import com.AkashSharma.AkashAssignment3.Repository.ToyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Service
@SuppressWarnings("unchecked")
public class ToyService {
    @Autowired
    private ToyRepository toyRepo;
    
    
    public String retrieveHP(HttpSession session, Model model) {
        ArrayList<CartPojo> cartPojoArrayList = (ArrayList<CartPojo>) session.getAttribute("cart");
        if(cartPojoArrayList == null)
            cartPojoArrayList = new ArrayList<>();
        if(cartPojoArrayList.size()==0)
            this.resetInCartStatus("N");
        session.setAttribute("cart", cartPojoArrayList);
        model.addAttribute("count", cartPojoArrayList.size());
        model.addAttribute("toys", this.toyRepo.findAll());
        return "toyPageHome";
    }

    public String deleteSession(HttpSession session) {
        resetInCartStatus("N");
        session.removeAttribute("cart");
        session.invalidate();
        return "redirect:/";
    }

    public String retrieveCart(HttpSession session, Model model) {
        ArrayList<CartPojo> cartPojoArrayList = (ArrayList<CartPojo>) session.getAttribute("cart");
        model.addAttribute("toys", cartPojoArrayList);
        this.resetInCartStatus("N");
        return "toyCartPage";
    }

    public String setToy(ToyPojo toy) {
        Long toyRepoLargestId = this.toyRepo.findLargestId();
        toy.setToyId(toyRepoLargestId!=null ? toyRepoLargestId+1 : 1l);
        this.toyRepo.save(toy);
        return "redirect:/retrieveAdminPage";
    }

    public String removeToyById(Long id, HttpSession session) {
        this.toyRepo.deleteById(id);
        ArrayList<CartPojo> cartPojoArrayList = (ArrayList<CartPojo>) session.getAttribute("cart");
        for(CartPojo i : cartPojoArrayList)
            if(i.getToyId().equals(id)){
                cartPojoArrayList.remove(i);
                session.setAttribute("cart", cartPojoArrayList);
                break;
            }
        return "redirect:/retrieveAdminPage";
    }

    public String setItemToCart(Long id, HttpSession session) {
        ToyPojo toy = toyRepo.findById(id).orElse(new ToyPojo());
        ArrayList<CartPojo> cartPojoArrayList = (ArrayList<CartPojo>) session.getAttribute("cart");
        if(toy!=null){
            Long count = 0l;
            count = cartPojoArrayList.stream().filter(c -> c.getToyId().equals(id)).count();
            if( !count.equals(0l)){
                toy.setIsCartItemActive("Y");
                this.toyRepo.save(toy);
                return "redirect:/";
            }
            CartPojo cartItem = new CartPojo(toy);
            Long i = 1l;
            for( CartPojo item : cartPojoArrayList){
                if(i<item.getCartId()){
                    i= item.getCartId();
                }
            }
            cartItem.setCartId(i==null || i.equals(1l) ? 1l : i+1);
            cartPojoArrayList.add(cartItem);
            session.setAttribute("cart", cartPojoArrayList);
        }
        return "redirect:/";
    }

    public String retrieveAdminPage(Model model) {
        model.addAttribute("toys", this.toyRepo.findAll());
        model.addAttribute("toy",new ToyPojo());
        resetInCartStatus("N");
        return "toyPageAdmin";
    }

    public String retrieveUpdateItemPage(Long id, Model model) {
        model.addAttribute("item", this.toyRepo.findById(id).orElse(new ToyPojo()));
        model.addAttribute("toy",new ToyPojo());
        return "toyUpdatePage";
    }

    public String removeItemFromCart(Long id, HttpSession session){
        ArrayList<CartPojo> cartPojoArrayList = (ArrayList<CartPojo>) session.getAttribute("cart");
        for(CartPojo i : cartPojoArrayList)
            if(i.getToyId().equals(id)){
                cartPojoArrayList.remove(i);
                session.setAttribute("cart", cartPojoArrayList);
                break;
            }
        ToyPojo toy = this.toyRepo.findById(id).orElse(null);
        if(toy!=null && toy.getIsCartItemActive().equals("Y")){
            toy.setIsCartItemActive("N");
            this.toyRepo.save(toy);
        }
        return "redirect:/retrieveCart";
    }

    public String updateToy(Long id, ToyPojo toy) {
        toy.setToyId(id);
        this.toyRepo.save(toy);
        return "redirect:/retrieveAdminPage";
    }

    private void resetInCartStatus(String flag) {
        ArrayList<ToyPojo> t = (ArrayList<ToyPojo>) this.toyRepo.findAll();
        if(t==null || t.size()==0)
            return;
        t.forEach(i->i.setIsCartItemActive(flag));
        this.toyRepo.saveAll(t);
    }
}
