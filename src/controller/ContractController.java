package controller;

import Pojo.Apply;
import Pojo.Checkout;
import Pojo.Contract;
import Pojo.HouseList;
import Pojo.ZuList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import service.ApplyService;
import service.CheckoutService;
import service.ContractService;
import service.HouseListService;
import service.ZuListService;

@Controller
public class ContractController {
    @Autowired
    private ContractService contractService;
    @Autowired
    private HouseListService houseListService;
    @Autowired
    private ApplyService applyService;
    @Autowired
    private ZuListService zuListService;
    @Autowired
    private CheckoutService checkoutService;

    //新增合同信息，修改房屋列表的状态，从申请列表中删除，增添到租赁列表当中
    @RequestMapping("/hetong/inserthetong")
    public String insertContract(Model model, Contract contract) {
        //新增合同信息
        contractService.insertContract(contract);
        Contract contract1 = contractService.findContract(contract.getHouse_id());
        //修改房屋列表状态
        HouseList houselist = houseListService.findHouseId(contract1.getHouse_id());
        houselist.setStatus("已租赁");
        houseListService.updateHouseStatus(houselist);
        //添加到租赁列表当中
        ZuList zulist = new ZuList();
        Apply apply = applyService.findByHouseId(contract.getHouse_id());
        zulist.setHouseId(contract.getHouse_id());
        zulist.setUserListId(apply.getUserListId());
        zulist.setContractId(contract1.getId());
        zulist.setPrice(apply.getPrice());
        zulist.setAddress(apply.getAddress());
        zuListService.insertZuList(zulist);
        //从申请列表中删除
        applyService.deleteByHouseId(contract1.getHouse_id());
        model.addAttribute("error", "zusuccess");
        return "redirect:/zulist/findzulist.action";

    }

    @RequestMapping("/hetong/seehetong")
    public String seehetong(String house_id, Model model) {
        Contract hetong = contractService.findContract(house_id);
        model.addAttribute("hetong", hetong);
        model.addAttribute("mainPage", "hetong.jsp");
        return "admin/main1";
    }

    @RequestMapping("/hetong/updatehetong")
    public String updateContract(String houseId, Model model) {
        Contract contract = contractService.findContract(houseId);
        model.addAttribute("hetong", contract);
        model.addAttribute("mainPage", "updateContract.jsp");
        return "admin/main1";
    }

    @RequestMapping("/hetong/changehetong")
    public String changeContract(Contract contract) {
        contractService.updateContract(contract);

        return "redirect:/zulist/findzulist.action";
    }
    //终止合同操作：删除合同，插入已退租列表，删除在租列表，删除房屋列表

    @RequestMapping("/hetong/deletehetong")
    public String deletehetong(String house_id, Model model) {
        contractService.deleteContract(house_id);
        ZuList zulist = zuListService.findZuList(house_id);
        Checkout checkout = new Checkout();
        checkout.setHouse_id(house_id);
        checkout.setAddress(zulist.getAddress());
        checkout.setStatus("已退租");
        checkout.setUserlist_id(zulist.getUserListId());
        checkoutService.insertCheckOut(checkout);
        houseListService.deleteHouseByHouseId(house_id);
        zuListService.deleteZuList(house_id);

        model.addAttribute("error", "checkoutsuccess");
        return "redirect:/zulist/findzulist.action";
    }

    @RequestMapping("/hetong/zukeseehetong")
    public String zukeseeContract(String houseId, Model model) {
        Contract contract = contractService.findContract(houseId);
        model.addAttribute("hetong", contract);
        model.addAttribute("mainPage", "showhetong.jsp");
        return "zuke/main";
    }
}
