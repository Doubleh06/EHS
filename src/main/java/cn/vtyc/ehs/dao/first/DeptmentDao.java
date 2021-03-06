package cn.vtyc.ehs.dao.first;

import cn.vtyc.ehs.core.BaseDao;
import cn.vtyc.ehs.entity.Deptment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DeptmentDao extends BaseDao<Deptment> {
    @Select("select dept_name from deptment where id = ${id}")
    String getNameById (@Param("id")Integer id);

    @Select("select dept_name from deptment")
    List<String> getAllDeptName ();

}
