package controller;


import Pojo.Checkout;
import Pojo.User;
import Pojo.UserList;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.CheckoutService;
import service.UserListService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("")
public class CheckoutController {
    @Autowired
    private CheckoutService checkoutService;
    @Autowired
    private UserListService userListService;

    @RequestMapping("/checkout/getallcheckout")
    public String getAllCheckout(Model model, @RequestParam(required = false, defaultValue = "1") Integer page,
                                 @RequestParam(required = false, defaultValue = "2") Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Checkout> checkout = checkoutService.getAllCheckOut();
        PageInfo<Checkout> p = new PageInfo<Checkout>(checkout);
        model.addAttribute("p", p);
        model.addAttribute("checkout", checkout);
        model.addAttribute("mainPage", "checkout.jsp");
        return "admin/main1";
    }

    //租客删除自己已退租列表
    @RequestMapping("/checkout/deletecheckout")
    public String deleteCheckout(Integer id) {
        checkoutService.deleteCheckOut(id);
        return "redirect:/checkout/getmycheckout.action";
    }

    //租客删除自己已退租列表
    @RequestMapping("/checkout/admindeletecheckout")
    public String adminDeleteCheckout(Integer id) {
        checkoutService.deleteCheckOut(id);
        return "redirect:/checkout/getallcheckout.action";
    }

    @RequestMapping("/checkout/getmycheckout")
    public String getMyCheckout(Model model, HttpSession httpSession, @RequestParam(required = false, defaultValue = "1") Integer page,
                                @RequestParam(required = false, defaultValue = "2") Integer pageSize) {
        User user1 = (User) httpSession.getAttribute("user");
        UserList userList = userListService.findHasUserList(user1.getId());
        PageHelper.startPage(page, pageSize);
        List<UserList> list = userListService.getMyCheckout(userList.getId());
        PageInfo<UserList> p = new PageInfo<UserList>(list);
        model.addAttribute("p", p);
        model.addAttribute("userlistcheck", list);
        model.addAttribute("mainPage", "mycheckout.jsp");
        return "zuke/main";
    }
}
