package org.javaboy.vhr.service;

import org.javaboy.vhr.mapper.EmployeeremoveMapper;
import org.javaboy.vhr.model.Employeeremove;
import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.model.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class EmployeeRemoveService {

    @Autowired
    EmployeeremoveMapper employeeremoveMapper;

    public RespPageBean getEmployeeRemoveByPage(Integer page, Integer size) {

        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Employeeremove> list = employeeremoveMapper.getEmployeeRemoveByPage(page, size);
        RespPageBean respPageBean = new RespPageBean();
        respPageBean.setData(list);
        respPageBean.setTotal(employeeremoveMapper.getTotal());

        return respPageBean;
    }

    public Integer deleteByPrimaryKey(Integer id) {
        return employeeremoveMapper.deleteByPrimaryKey(id);
    }

    public Integer insertSelective(Employeeremove employeeremove) {
        return employeeremoveMapper.insertSelective(employeeremove);
    }
}
