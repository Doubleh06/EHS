package cn.vtyc.ehs.controller.maintenanceController;

import cn.vtyc.ehs.controller.BaseController;
import cn.vtyc.ehs.core.JSONResult;
import cn.vtyc.ehs.core.Result;
import cn.vtyc.ehs.core.jqGrid.JqGridResult;
import cn.vtyc.ehs.dto.AccidentTypeJqGridParam;
import cn.vtyc.ehs.entity.AccidentType;
import cn.vtyc.ehs.service.AccidentTypeService;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/maintenance/accidentType")
public class AccidentTypeMaintenanceController extends BaseController {

    @Autowired
    private AccidentTypeService accidentTypeService;

    @RequestMapping(value = "/list")
    public String deptList(Model model){
        model.addAttribute("menus", getMenus("accidentType"));
        return "/maintenance/accidentTypeList";
    }

    @RequestMapping(value = "/grid")
    @ResponseBody
    public Result grid( AccidentTypeJqGridParam param) {
        PageInfo<AccidentType> pageInfo = accidentTypeService.selectByJqGridParam(param);
        JqGridResult<AccidentType> result = new JqGridResult<>();
        //当前页
        result.setPage(pageInfo.getPageNum());
        //数据总数
        result.setRecords(pageInfo.getTotal());
        //总页数
        result.setTotal(pageInfo.getPages());
        //当前页数据
        result.setRows(pageInfo.getList());
        return new JSONResult(result);
    }

    @RequestMapping("/insert")
    @ResponseBody
    public Result insert(@Valid @RequestBody JSONObject dto) {
        String name = dto.getString("name");
        AccidentType accidentType = new AccidentType(name);
        accidentTypeService.insert(accidentType);
        return OK;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(@RequestParam Integer id) {
        accidentTypeService.delete(id);
        return OK;
    }
    @RequestMapping("/get")
    @ResponseBody
    public Result get(@RequestParam Integer id) {
        return new JSONResult(accidentTypeService.select(id));
    }
    @RequestMapping("/update")
    @ResponseBody
    public Result update(@Valid @RequestBody JSONObject dto) {
        Integer id = dto.getInteger("id");
        String name = dto.getString("name");
        AccidentType accidentType = new AccidentType();
        accidentType.setId(id);
        accidentType.setName(name);
        accidentTypeService.update(accidentType);
        return OK;
    }
}
