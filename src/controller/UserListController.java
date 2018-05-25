package controller;

import Pojo.User;
import Pojo.UserList;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.UserListService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserListController {

    @Autowired
    private UserListService userlistService;


    @RequestMapping("/findhasuserlist")
    public String findHasUserList(HttpSession httpSession, Model model) throws Exception {
        User user1 = (User) httpSession.getAttribute("user");
        Integer user_id = user1.getId();
        UserList userList = userlistService.findHasUserList(user_id);
        model.addAttribute("userlist", userList);
        model.addAttribute("mainPage", "updateUserList.jsp");
        return "zuke/main";

    }

    //查找并更新用户信息
    @RequestMapping("/checkuserlist")
    public String checkUserList(Model model, UserList userList, HttpSession httpSession) throws Exception {
        if (userList.getId() == null) {
            String idCard = userList.getIdCard();
            UserList list = userlistService.checkUserList(idCard);

            if (list != null) {
                model.addAttribute("error", "该身份证已被绑定,一个身份证号码只能被一个账户绑定！");
                model.addAttribute("mainPage", "updateUserList.jsp");
                model.addAttribute("userlist", userList);
            } else {
                User user1 = (User) httpSession.getAttribute("user");
                Integer user_id = user1.getId();
                userList.setUser_id(user_id);
                userlistService.insertUserList(userList);
                UserList list1 = userlistService.checkUserList(idCard);
                model.addAttribute("error", "资料完善成功");
                model.addAttribute("mainPage", "updateUserList.jsp");
                model.addAttribute("userlist", list1);
            }


        } else {

            UserList list = userlistService.findUserListUpdate(userList);
            if (list != null) {
                model.addAttribute("error", "该身份证号码已被绑定");
                model.addAttribute("mainPage", "updateUserList.jsp");
                model.addAttribute("userlist", userList);
            } else {
                userlistService.updateUserList(userList);
                model.addAttribute("error", "更新成功");
                model.addAttribute("mainPage", "updateUserList.jsp");
                model.addAttribute("userlist", userList);
            }

        }
        return "zuke/main";
    }

    @RequestMapping("/findalluserlist")
    public String findAllUserList(Model model, @RequestParam(required = false, defaultValue = "1") Integer page,
                                  @RequestParam(required = false, defaultValue = "2") Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<UserList> userList = userlistService.findAllUserList();
        PageInfo<UserList> p = new PageInfo<>(userList);
        model.addAttribute("userlist", userList);
        model.addAttribute("p", p);
        model.addAttribute("mainPage", "userList.jsp");
        return "admin/main1";

    }

    //删除用户信息
    @RequestMapping("/deleteuserlist")
    public String deleteUserList(Model model, Integer id) {
        userlistService.deleteUserList(id);
        model.addAttribute("error", "deletesuccess");
        return "redirect:findalluserlist.action";
    }
}
