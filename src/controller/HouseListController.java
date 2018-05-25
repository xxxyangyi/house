package controller;

import Pojo.HouseList;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.HouseListService;

import java.util.List;

@Controller

public class HouseListController {
    @Autowired
    private HouseListService houseListService;

    @RequestMapping("/houselist")
    public String houseList(Model model, @RequestParam(required = false, defaultValue = "1") Integer page,
                            @RequestParam(required = false, defaultValue = "2") Integer pageSize) {

        PageHelper.startPage(page, pageSize);
        List<HouseList> houseLists = houseListService.selectAll();
        PageInfo<HouseList> p = new PageInfo<>(houseLists);


        model.addAttribute("p", p);
        model.addAttribute("houselist", houseLists);
        model.addAttribute("mainPage", "houselist.jsp");
        return "zuke/main";
    }

    @RequestMapping("/ahouselist")
    public String oneHouseList(Model model, @RequestParam(required = false, defaultValue = "1") Integer page,
                             @RequestParam(required = false, defaultValue = "2") Integer pageSize) {

        PageHelper.startPage(page, pageSize);
        List<HouseList> houseList = houseListService.selectAll();
        PageInfo<HouseList> p = new PageInfo<>(houseList);


        model.addAttribute("p", p);
        model.addAttribute("houselist", houseList);
        model.addAttribute("mainPage", "ahouselist.jsp");
        return "admin/main1";
    }

    @RequestMapping("/addhouse")
    public String addhouse(Model model, HouseList houselist) {

        String houseid = houselist.getHouseId();
        HouseList houselist1 = houseListService.findHouseId(houseid);
        if (houselist1 != null) {
            model.addAttribute("error", "该房屋id已存在");
            model.addAttribute("houselist", houselist);
            model.addAttribute("mainPage", "addhouse.jsp");
            return "admin/main1";
        } else {
            model.addAttribute("error", "添加成功");
            houseListService.insertHouse(houselist);
            model.addAttribute("houselist", houselist);
            model.addAttribute("mainPage", "addhouse.jsp");
            return "admin/main1";
        }
    }

    @RequestMapping("/toaddhouse")
    public String addHouse(Model model) {
        model.addAttribute("mainPage", "addhouse.jsp");

        return "admin/main1";
    }

    @RequestMapping("/deletehouse")
    public String deleteHouse(Integer id) {
        houseListService.deleteHouse(id);


        return "redirect:ahouselist.action";
    }

    @RequestMapping("/toahouselist")
    public String toahouselist() {


        return "ahouselist.action";
    }

    @RequestMapping("/findid")
    public String findId(Integer id, Model model) {
        HouseList list = houseListService.findId(id);
        model.addAttribute("houselist", list);
        model.addAttribute("mainPage", "changehouse.jsp");
        return "admin/main1";
    }

    @RequestMapping("/findhouseidupdate")
    public String findhouseidupdate(HouseList houselist, Model model) {
        HouseList list = houseListService.findHouseIdUpdate(houselist);
        if (list != null) {
            model.addAttribute("houselist", houselist);
            model.addAttribute("mainPage", "changehouse.jsp");
            model.addAttribute("error", "该房屋id已存在");
            return "admin/main1";
        } else {
            houseListService.updateHouse(houselist);
            model.addAttribute("houselist", houselist);
            model.addAttribute("mainPage", "changehouse.jsp");
            model.addAttribute("error", "更新成功");
            return "admin/main1";
        }
    }

}
