package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.model.Employeetrain;

import java.util.List;

@Mapper
public interface EmployeetrainMapper {

    List<Employeetrain> getEmployeeTrainByPage(@Param("page") Integer page, @Param("size") Integer size);

    Long getTotal();

    int deleteByPrimaryKey(Integer id);

    int insert(Employeetrain record);

    int insertSelective(Employeetrain record);

    Employeetrain selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employeetrain record);

    int updateByPrimaryKey(Employeetrain record);
}