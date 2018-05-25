package controller;

import Pojo.QueryVo;
import Pojo.Solve;
import Pojo.User;
import Pojo.UserList;
import Pojo.Wrong;
import Pojo.ZuList;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.PaidService;
import service.SolveService;
import service.UserListService;
import service.ZuListService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/wrong")
public class WrongController {
    @Autowired
    private SolveService solveService;
    @Autowired
    private UserListService userListService;
    @Autowired
    private PaidService paidService;
    @Autowired
    private ZuListService zuListService;

    //管理员查找所有已处理的报障
    @RequestMapping("/selectall")
    public String selectAll(Model model, QueryVo vo, @RequestParam(required = false, defaultValue = "1") Integer page,
                            @RequestParam(required = false, defaultValue = "2") Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Solve> list = solveService.selectAll(vo);
        PageInfo<Solve> p = new PageInfo<>(list);
        Integer count = solveService.selectCount(vo);
        model.addAttribute("solve", list);
        model.addAttribute("count", count);
        model.addAttribute("p", p);
        model.addAttribute("mainPage", "solve.jsp");
        model.addAttribute("vo", vo);
        return "admin/main1";
    }

    //租客查找自己已处理的报障
    @RequestMapping("/findmysolve")
    public String findMySolve(HttpSession httpSession, Model model, QueryVo vo, @RequestParam(required = false, defaultValue = "1") Integer page,
                              @RequestParam(required = false, defaultValue = "2") Integer pageSize) {
        User user1 = (User) httpSession.getAttribute("user");
        UserList userList = userListService.findHasUserList(user1.getId());

        vo.setUserListId(userList.getId());
        PageHelper.startPage(page, pageSize);
        List<Solve> list = solveService.selectAll(vo);
        PageInfo<Solve> p = new PageInfo<>(list);
        Integer count = solveService.selectCount(vo);
        model.addAttribute("solve", list);
        model.addAttribute("count", count);
        model.addAttribute("p", p);
        model.addAttribute("mainPage", "mysolve.jsp");
        model.addAttribute("vo", vo);
        return "zuke/main";
    }

    //管理员删除已处理报障记录
    @RequestMapping("/deletesolve")
    public String deleteSolve(Integer id) {
        solveService.deleteSolve(id);
        return "redirect:selectAll.action";
    }

    //zuke删除自己的已处理报障记录
    @RequestMapping("/zukedeletesolve")
    public String zukeDeleteSolve(Integer id) {
        solveService.deleteSolve(id);
        return "redirect:findmypaid.action";
    }

    //租客跳到我要报障页面
    @RequestMapping("/showaddwrong")
    public String showAddWrong(HttpSession httpSession, Model model, @RequestParam(required = false, defaultValue = "1") Integer page,
                               @RequestParam(required = false, defaultValue = "2") Integer pageSize) throws Exception {
        User user1 = (User) httpSession.getAttribute("user");
        UserList userList = userListService.findHasUserList(user1.getId());
        PageHelper.startPage(page, pageSize);
        List<ZuList> list = zuListService.findZuListByUid(userList.getId());
        PageInfo<ZuList> p = new PageInfo<ZuList>(list);
        model.addAttribute("zulist", list);
        model.addAttribute("p", p);
        model.addAttribute("mainPage", "showaddwrong.jsp");
        return "zuke/main";
    }

    //点击报障后跳转到添加报障信息页面
    @RequestMapping("/addWrong")
    public String addWrong(Integer id, Model model) {
        ZuList zulist = paidService.findzukeZuList(id);
        model.addAttribute("zulist", zulist);
        model.addAttribute("mainPage", "addwrong.jsp");
        return "zuke/main";
    }

    //添加报障信息到wrong表
    @RequestMapping("/insertwrong")
    public String insertWrong(Wrong wrong, Model model) {
        solveService.insertWrong(wrong);
        model.addAttribute("error", "insertWrong");

        return "redirect:showaddwrong.action";
    }

    //管理员查看所有未处理报障
    @RequestMapping("/wronglist")
    public String wrongList(Model model, @RequestParam(required = false, defaultValue = "1") Integer page,
                            @RequestParam(required = false, defaultValue = "2") Integer pageSize) {
        QueryVo vo = new QueryVo();
        PageHelper.startPage(page, pageSize);
        List<Wrong> list = solveService.findWrong(vo);
        PageInfo<Wrong> p = new PageInfo<>(list);
        model.addAttribute("wrong", list);
        model.addAttribute("p", p);
        model.addAttribute("mainPage", "wrong.jsp");
        return "admin/main1";
    }

    //租客查看自己的未处理报障
    @RequestMapping("/mywronglist")
    public String myWrongList(Model model, HttpSession httpSession, @RequestParam(required = false, defaultValue = "1") Integer page,
                              @RequestParam(required = false, defaultValue = "2") Integer pageSize) {
        User user1 = (User) httpSession.getAttribute("user");
        UserList userList = userListService.findHasUserList(user1.getId());
        QueryVo vo = new QueryVo();
        vo.setUserListId(userList.getId());
        PageHelper.startPage(page, pageSize);
        List<Wrong> list = solveService.findWrong(vo);
        PageInfo<Wrong> p = new PageInfo<>(list);
        model.addAttribute("p", p);
        model.addAttribute("wrong", list);
        model.addAttribute("mainPage", "mywrong.jsp");
        return "zuke/main";
    }

    //管理员处理报障
    @RequestMapping("/gotosolve")
    public String gotoSolve(Integer id, Model model) {

        Wrong wrong = solveService.findById(id);
        Solve solve = new Solve();
        solve.setHouseId(wrong.getHouseId());
        solve.setAddress(wrong.getAddress());
        solve.setDate(wrong.getDate());
        solve.setDetail(wrong.getDetail());
        solve.setName(wrong.getName());
        solve.setUserListId(wrong.getUserListId());
        solve.setStatus("已处理");
        solveService.gotoSolve(id, solve);
        model.addAttribute("error", "duesucess");
        return "redirect:selectAll.action";
    }
}
