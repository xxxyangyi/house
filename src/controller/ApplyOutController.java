package controller;

import Pojo.ApplyOut;
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
import service.ApplyOutService;
import service.UserListService;
import service.ZuListService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ApplyOutController {
    @Autowired
    private ZuListService zuListService;
    @Autowired
    private ApplyOutService applyoutService;
    @Autowired
    private UserListService userlistService;

    //插入退租信息
    @RequestMapping("/applyout/insertapplyout")
    public String insertapplyout(String house_id, Model model) {
        ZuList zulist = zuListService.findZuList(house_id);
        applyoutService.insertApplyOut(zulist);
        model.addAttribute("error", "applysuccess");
        return "redirect:/zulist/myzulist.action";
    }

    //查看退租申请
    @RequestMapping("/applyout/findallapplyout")
    public String findallapplyout(Model model, @RequestParam(required = false, defaultValue = "1") Integer page,
                                  @RequestParam(required = false, defaultValue = "2") Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<ApplyOut> applyout = applyoutService.findAllApplyOut();
        PageInfo<ApplyOut> p = new PageInfo<ApplyOut>(applyout);
        model.addAttribute("applyout", applyout);
        model.addAttribute("p", p);
        model.addAttribute("mainPage", "applyout.jsp");
        return "admin/main1";
    }

    //管理员拒绝退租申请
    @RequestMapping("/applyout/refuseapplyout")
    public String refuseapplyout(Model model, Integer id) {
        ApplyOut applyout = new ApplyOut();
        applyout.setId(id);
        applyout.setStatus("已拒绝");
        applyoutService.updateApplyOut(applyout);
        model.addAttribute("mainPage", "applyout.jsp");
        return "redirect:findAllApplyOut.action";
    }

    //管理员同意退租申请
    @RequestMapping("/applyout/agreeapplyout")
    public String agreeapplyout(Model model, Integer id) {
        applyoutService.agreeApplyOut(id);
        model.addAttribute("error", "applyoutsucess");
        return "redirect:findAllApplyOut.action";
    }

    //删除申请退租列表
    @RequestMapping("/applyout/deleteapplyout")
    public String deleteapplyout(Model model, Integer id) {
        applyoutService.deleteApplyOut(id);
        model.addAttribute("error", "deletesucess");
        return "redirect:findAllApplyOut.action";
    }

    //租客查看自己的 退房申请
    @RequestMapping("/applyout/getmyapplyout")
    public String getmyapplyout(Model model, HttpSession httpSession, @RequestParam(required = false, defaultValue = "1") Integer page,
                                @RequestParam(required = false, defaultValue = "2") Integer pageSize) {
        User user1 = (User) httpSession.getAttribute("user");
        UserList userList = userlistService.findHasUserList(user1.getId());
        PageHelper.startPage(page, pageSize);
        List<UserList> list = userlistService.getMyApplyOut(userList.getId());
        PageInfo<UserList> p = new PageInfo<UserList>(list);
        model.addAttribute("userlist", list);
        model.addAttribute("p", p);
        model.addAttribute("mainPage", "myapplyout.jsp");
        return "zuke/main";
    }
}
