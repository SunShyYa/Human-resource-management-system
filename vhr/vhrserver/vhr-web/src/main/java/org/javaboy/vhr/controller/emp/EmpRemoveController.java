package org.javaboy.vhr.controller.emp;

import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.model.Employeeremove;
import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.service.DepartmentService;
import org.javaboy.vhr.service.EmployeeRemoveService;
import org.javaboy.vhr.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("/employee/remove")
public class EmpRemoveController {

    @Autowired
    EmployeeRemoveService employeeRemoveService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/")
    public RespPageBean getEmployeeRemoveByPage(@RequestParam(defaultValue = "1") Integer page,
                                                     @RequestParam(defaultValue = "10") Integer size) {

        return employeeRemoveService.getEmployeeRemoveByPage(page, size);
    }

    @DeleteMapping("/{id}")
    public RespBean deleteByPrimaryKey(@PathVariable("id")Integer id) {
        if(employeeRemoveService.deleteByPrimaryKey(id) == 1) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }
    @PostMapping("/")
    public RespBean insertSelective(@RequestBody Employeeremove employeeremove) {
        System.out.println(employeeremove);
        employeeremove.setRemovedate(new Date());

        if(employeeRemoveService.insertSelective(employeeremove) == 1) {
            employeeService.updateDepartment(employeeremove.getEid(), employeeremove.getAfterdepid());
            employeeService.updatePosition(employeeremove.getEid(), employeeremove.getAfterjobid());
            return RespBean.ok("添加成功");
        }
        return  RespBean.error("添加失败");
    }
}
