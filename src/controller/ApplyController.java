package controller;

import Pojo.Apply;
import Pojo.HouseList;
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
import service.ApplyService;
import service.HouseListService;
import service.UserListService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ApplyController {
    @Autowired
    private UserListService userlistService;
    @Autowired
    private HouseListService houselistService;
    @Autowired
    private ApplyService applyService;

    //申请看房
    @RequestMapping("/applycheckuserlist")
    public String applyCheckUserList(HttpSession httpSession, Model model, Integer id) {
        User user1 = (User) httpSession.getAttribute("user");
        Integer user_id = user1.getId();
        UserList list = userlistService.findHasUserList(user_id);
        if (list == null) {
            model.addAttribute("error", "applycheck");
            return "redirect:houselist.action";
        } else {
            HouseList houselist = houselistService.findId(id);
            houselist.setStatus("已被申请");
            houselistService.updateHouseStatus(houselist);
            Integer userlist_id = list.getId();
            Apply apply = new Apply();
            apply.setHouseId(houselist.getHouseId());
            apply.setAddress(houselist.getAddress());
            apply.setPrice(houselist.getPrice());
            apply.setArea(houselist.getArea());
            apply.setStatus("申请中");
            apply.setUserListId(userlist_id);
            applyService.insertApply(apply);
            model.addAttribute("error", "applysuccess");
            return "redirect:houselist.action";


        }

    }

    //管理员查看申请看房列表
    @RequestMapping("/findapplylist")
    public String findApplyList(Model model, @RequestParam(required = false, defaultValue = "1") Integer page,
                                @RequestParam(required = false, defaultValue = "2") Integer pageSize) throws Exception {
        PageHelper.startPage(page, pageSize);
        List<Apply> applyList = applyService.findApplyList();
        PageInfo<Apply> p = new PageInfo<Apply>(applyList);
        model.addAttribute("applylist", applyList);
        model.addAttribute("p", p);
        model.addAttribute("mainPage", "applylist.jsp");
        return "admin/main1";
    }

    @RequestMapping("/applychangehousestatus")
    public String applyChangeHouseStatus(HttpSession httpSession, Model model, String house_id) throws Exception {
        User user1 = (User) httpSession.getAttribute("user");
        Integer user_id = user1.getId();
        UserList userList = userlistService.findHasUserList(user_id);
        HouseList houselist = houselistService.findHouseId(house_id);
        houselist.setStatus("已租赁");
        houselistService.updateHouseStatus(houselist);
        ZuList zulist = new ZuList();
        zulist.setHouseId(house_id);
        zulist.setPrice(houselist.getPrice());
        zulist.setAddress(houselist.getAddress());

        return "";
    }

    //管理员拒绝看房申请
    @RequestMapping("/refuseapply")
    public String refuseApply(String house_id, Model model) {
        HouseList houselist = new HouseList();
        houselist.setHouseId(house_id);
        houselist.setStatus("未租赁");
        applyService.refuseApply(houselist);

        return "redirect:findapplylist.action";
    }

    //租客查看自己的 看房申请
    @RequestMapping("/getmyapply")
    public String getMyApply(Model model, HttpSession httpSession, @RequestParam(required = false, defaultValue = "1") Integer page,
                             @RequestParam(required = false, defaultValue = "2") Integer pageSize) {
        User user1 = (User) httpSession.getAttribute("user");
        UserList userList = userlistService.findHasUserList(user1.getId());
        PageHelper.startPage(page, pageSize);
        List<UserList> list = userlistService.getMyApply(userList.getId());
        PageInfo<UserList> p = new PageInfo<UserList>(list);
        model.addAttribute("userlist", list);
        model.addAttribute("p", p);
        model.addAttribute("mainPage", "myapply.jsp");
        return "zuke/main";
    }


}
