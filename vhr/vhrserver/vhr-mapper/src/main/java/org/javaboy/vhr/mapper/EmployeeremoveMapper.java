package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.model.Employeeremove;

import java.util.List;

@Mapper
public interface EmployeeremoveMapper {

    List<Employeeremove> getEmployeeRemoveByPage(@Param("page") Integer page, @Param("size") Integer size);

    int deleteByPrimaryKey(Integer id);

    Long getTotal();

    int insert(Employeeremove record);

    int insertSelective(Employeeremove record);

    Employeeremove selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employeeremove record);

    int updateByPrimaryKey(Employeeremove record);
}