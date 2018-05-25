package controller;

import Pojo.Contract;
import Pojo.User;
import Pojo.UserList;
import Pojo.ZuList;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.UserListService;
import service.ZuListService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/zulist")
public class ZulistController {
    @Autowired
    private ZuListService zuListService;
    @Autowired
    private UserListService userListService;

    //跳到增添合同的页面
    @RequestMapping("/toaddhetong")
    public String toAddContract(Model model, String house_id) {
        Contract contract = new Contract();
        contract.setHouse_id(house_id);
        model.addAttribute("contract", contract);
        model.addAttribute("mainPage", "addhetong.jsp");

        return "admin/main1";
    }

    //管理员查看所有在租列表
    @RequestMapping("/findzulist")
    public String findZuList(Model model, @RequestParam(required = false, defaultValue = "1") Integer page,
                             @RequestParam(required = false, defaultValue = "2") Integer pageSize) throws Exception {
        PageHelper.startPage(page, pageSize);
        List<ZuList> zuUserList = zuListService.findZuUserList();
        PageInfo<ZuList> p = new PageInfo<>(zuUserList);
        model.addAttribute("p", p);
        model.addAttribute("zuUserList", zuUserList);
        model.addAttribute("mainPage", "zuUserList.jsp");
        return "admin/main1";
    }

    //查看我的在租列表
    @RequestMapping("/myzulist")
    public String myZuList(Model model, HttpSession httpSession, @RequestParam(required = false, defaultValue = "1") Integer page,
                           @RequestParam(required = false, defaultValue = "2") Integer pageSize) throws Exception {

        User user1 = (User) httpSession.getAttribute("user");
        UserList userList = userListService.findHasUserList(user1.getId());
        PageHelper.startPage(page, pageSize);
        List<UserList> list = userListService.getUserZuList(userList.getId());
        PageInfo<UserList> p = new PageInfo<>(list);
        model.addAttribute("userlistzu", list);
        model.addAttribute("p", p);
        model.addAttribute("mainPage", "myzulist.jsp");

        return "zuke/main";
    }

}
