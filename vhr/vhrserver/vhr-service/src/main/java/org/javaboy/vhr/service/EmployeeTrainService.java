package org.javaboy.vhr.service;

import org.javaboy.vhr.mapper.EmployeeMapper;
import org.javaboy.vhr.mapper.EmployeetrainMapper;
import org.javaboy.vhr.model.Employeetrain;
import org.javaboy.vhr.model.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeTrainService {
    @Autowired
    EmployeetrainMapper employeetrainMapper;

    public RespPageBean getEmployeeTrainByPage(Integer page, Integer size) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Employeetrain> list = employeetrainMapper.getEmployeeTrainByPage(page, size);
        RespPageBean respPageBean = new RespPageBean();
        respPageBean.setData(list);
        respPageBean.setTotal(employeetrainMapper.getTotal());
        return respPageBean;
    }

    public Integer insertSelective(Employeetrain employeetrain) {
        return employeetrainMapper.insertSelective(employeetrain);
    }

    public Integer deleteByPrimaryKey(Integer id) {
        return employeetrainMapper.deleteByPrimaryKey(id);
    }

    public Integer updateByPrimaryKeySelective(Employeetrain employeetrain) {
        return employeetrainMapper.updateByPrimaryKeySelective(employeetrain);
    }
}
