package org.javaboy.vhr.controller.emp;

import org.javaboy.vhr.model.Employeetrain;
import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.service.EmployeeTrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee/train")
public class EmpTrainController {

    @Autowired
    EmployeeTrainService employeeTrainService;

    @GetMapping("/")
    public RespPageBean getEmployeeTrainByPage(@RequestParam(defaultValue = "1") Integer page,
                                               @RequestParam(defaultValue = "10") Integer size) {
        return employeeTrainService.getEmployeeTrainByPage(page, size);
    }

    @DeleteMapping("/{id}")
    public RespBean deleteTrainById(@PathVariable("id") Integer id) {
        if(employeeTrainService.deleteByPrimaryKey(id) == 1) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @PostMapping("/")
    public RespBean addTrain(@RequestBody Employeetrain employeetrain) {

        if(employeeTrainService.insertSelective(employeetrain) == 1) {
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @PutMapping("/")
    public RespBean updateTrain(@RequestBody Employeetrain employeetrain) {
        if(employeeTrainService.updateByPrimaryKeySelective(employeetrain) == 1) {
            return RespBean.ok("修改成功");
        }
        return RespBean.error("修改失败");
    }


}
