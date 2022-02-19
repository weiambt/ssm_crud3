package top.ambtwill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ambtwill.dao.DepartmentMapper;
import top.ambtwill.pojo.Department;

import java.util.List;

/*
    2022/2/14 11:20
    @author 张渭
    Project Name:ssm_crud
     
    theme:
*/
@Service
public class DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    public List<Department> getDepts(){
         return departmentMapper.selectAll();
    }

}
