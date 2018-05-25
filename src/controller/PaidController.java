package controller;

import Pojo.Paid;
import Pojo.QueryVo;
import Pojo.ToPaid;
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
import service.PaidService;
import service.ToPaidService;
import service.UserListService;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

;

@Controller
@RequestMapping("/paid")
public class PaidController {
    @Autowired
    private PaidService paidService;
    @Autowired
    private ToPaidService topaidService;
    @Autowired
    private UserListService userlistService;

    //管理员查找所有已缴租金列表
    @RequestMapping("/selectall")
    public String selectAll(Model model, QueryVo vo, @RequestParam(required = false, defaultValue = "1") Integer page,
                            @RequestParam(required = false, defaultValue = "2") Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Paid> list = paidService.selectAll(vo);
        PageInfo<Paid> p = new PageInfo<>(list);
        Double sum = paidService.selectSum(vo);
        model.addAttribute("paid", list);
        model.addAttribute("sum", sum);
        model.addAttribute("p", p);
        model.addAttribute("mainPage", "paid.jsp");
        model.addAttribute("vo", vo);
        return "admin/main1";
    }

    //租客查找自己已缴租金列表
    @RequestMapping("/findmypaid")
    public String findMyPaid(HttpSession httpSession, Model model, QueryVo vo, @RequestParam(required = false, defaultValue = "1") Integer page,
                             @RequestParam(required = false, defaultValue = "2") Integer pageSize) {
        User user1 = (User) httpSession.getAttribute("user");
        UserList userList = userlistService.findHasUserList(user1.getId());

        vo.setUserListId(userList.getId());
        PageHelper.startPage(page, pageSize);
        List<Paid> list = paidService.selectAll(vo);
        PageInfo<Paid> p = new PageInfo<>(list);
        Double sum = paidService.selectSum(vo);
        model.addAttribute("paid", list);
        model.addAttribute("sum", sum);
        model.addAttribute("p", p);
        model.addAttribute("mainPage", "mypaid.jsp");
        model.addAttribute("vo", vo);
        return "zuke/main";
    }

    //管理员删除已缴租金记录
    @RequestMapping("/deletepaid")
    public String deletePaid(Integer id) {
        paidService.deletePaid(id);
        return "redirect:selectAll.action";
    }

    //zuke删除已缴租金记录
    @RequestMapping("/zukedeletepaid")
    public String zukeDeletePaid(Integer id) {
        paidService.deletePaid(id);
        return "redirect:findmypaid.action";
    }

    //跳到我要收租页面
    @RequestMapping("/showaddpaid")
    public String showAddPaid(Model model, @RequestParam(required = false, defaultValue = "1") Integer page,
                              @RequestParam(required = false, defaultValue = "2") Integer pageSize) throws Exception {
        PageHelper.startPage(page, pageSize);
        List<ZuList> list = paidService.findZuUserList();
        PageInfo<ZuList> p = new PageInfo<ZuList>(list);
        model.addAttribute("zulist", list);
        model.addAttribute("p", p);
        model.addAttribute("mainPage", "showaddpaid.jsp");
        return "admin/main1";
    }

    //点击收租后跳转到添加租金信息页面
    @RequestMapping("/addpaid")
    public String addPaid(Integer id, Model model) {
        ZuList zulist = paidService.findzukeZuList(id);
        model.addAttribute("zulist", zulist);
        model.addAttribute("mainPage", "addpaid.jsp");
        return "admin/main1";
    }

    //添加租金信息到topaid表
    @RequestMapping("/inserttopaid")
    public String inserttopaid(ToPaid topaid, Model model) {
        topaidService.insertToPaid(topaid);
        model.addAttribute("error", "insertToPaid");

        return "redirect:showaddpaid.action";
    }

    //管理员查看所有未缴租金信息
    @RequestMapping("/topaidlist")
    public String toPaidList(Model model, @RequestParam(required = false, defaultValue = "1") Integer page,
                             @RequestParam(required = false, defaultValue = "2") Integer pageSize) {
        QueryVo vo = new QueryVo();
        PageHelper.startPage(page, pageSize);
        List<ToPaid> list = topaidService.findToPaid(vo);
        PageInfo<ToPaid> p = new PageInfo<ToPaid>(list);
        model.addAttribute("topaid", list);
        model.addAttribute("p", p);
        model.addAttribute("mainPage", "topaid.jsp");
        return "admin/main1";
    }

    //租客查看自己的未缴租金
    @RequestMapping("/mytopaidlist")
    public String my2PaidList(Model model, HttpSession httpSession, @RequestParam(required = false, defaultValue = "1") Integer page,
                               @RequestParam(required = false, defaultValue = "2") Integer pageSize) {
        User user1 = (User) httpSession.getAttribute("user");
        UserList userList = userlistService.findHasUserList(user1.getId());
        QueryVo vo = new QueryVo();
        vo.setUserListId(userList.getId());
        PageHelper.startPage(page, pageSize);
        List<ToPaid> toPaid = topaidService.findToPaid(vo);
        PageInfo<ToPaid> p = new PageInfo<ToPaid>(toPaid);
        model.addAttribute("p", p);
        model.addAttribute("topaid", toPaid);
        model.addAttribute("mainPage", "mytopaid.jsp");
        return "zuke/main";
    }

    //租客进行支付操作
    @RequestMapping("/gotopay")
    public String gotoPay(Integer id, Model model) {
        Date dt = new Date();
        SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd");
        String payDate = matter1.format(dt);
        ToPaid topaid = topaidService.findById(id);
        Paid paid = new Paid();
        paid.setHouseId(topaid.getHouseId());
        paid.setAddress(topaid.getAddress());
        paid.setPrice(topaid.getPrice());
        paid.setDate(topaid.getDate());
        paid.setPayDate(payDate);
        paid.setName(topaid.getName());
        paid.setUserListId(topaid.getUserListId());
        paid.setStatus("租金已缴");
        topaidService.gotoPay(id, paid);
        model.addAttribute("error", "paysucess");
        return "redirect:findmypaid.action";
    }
}
