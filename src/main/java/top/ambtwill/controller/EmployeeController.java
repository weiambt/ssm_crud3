package top.ambtwill.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import top.ambtwill.pojo.Department;
import top.ambtwill.pojo.Employee;
import top.ambtwill.pojo.Msg;
import top.ambtwill.service.EmployeeService;

import java.util.List;

/*
    2022/2/13 16:05
    @author 张渭
    Project Name:ssm_crud
     
    theme:
*/
@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping("/list2")
    public String getByAjax(){
        return "list2";
    }


    @ResponseBody
    @RequestMapping(value = "/del/{ids}")
    public Msg deleteEmp(@PathVariable("ids") String  ids){
        if(ids.contains("-")){//有短横线就批量删除
            String[] str = ids.split("-");
            for (String s : str) {
                int i = Integer.parseInt(s);
                employeeService.deleteEmp(i);
            }
        }
        else{//删除一个
            employeeService.deleteEmp(Integer.parseInt(ids));
        }
        return Msg.success();


    }

    /**
     * 更新员工，这里的ＳＱＬ有改动
     * @param employee
     * @return
     */
    @RequestMapping(value = "/update/{empId}",method = RequestMethod.PUT)
    @ResponseBody
    public Msg updateEmp(Employee employee){
        System.out.println(employee);

        employeeService.updateEmp(employee);
        return Msg.success();
    }


    /**
     * 根据id回显数据
     * @param id
     * @return
     */
    @RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Msg getEmp(@PathVariable("id")Integer id){
        Employee employee = employeeService.getEmp(id);
        System.out.println(employee);
        return Msg.success().add("emp",employee);
    }
    /**
     * 保存员工
     * @param employee
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveEmp")
    public Msg saveEmp(Employee employee){
        employeeService.saveEmp(employee);
        return Msg.success();
    }

    /**
     * 检查用户名是否唯一
     * @param empName
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkUser")
    public Msg checkUser(String empName){
        boolean res = employeeService.checkUser(empName);
        if(res==true)
            return Msg.success();
        else
            return Msg.fail().add("va_msg","用户名已被注册");
    }
    /**
     * 分页查员工数据，el表达式方式
     * @return
     */
    @RequestMapping("/emps")
    public String getEmps(@RequestParam(value = "pn",defaultValue = "1")Integer pn,Model model){
        //引入PageHelper分页插件
        PageHelper.startPage(pn,5);//第pn页（页码为pn），每页5条数据

        List<Employee> emps = employeeService.getAll();
        // for (Employee emp : emps) {
        //     System.out.println(emp);
        // }
        //使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了
        //封装了详细的分页信息，包括有我们查询的数据,传入连续显示的页数
        PageInfo page=new PageInfo(emps,5);
        model.addAttribute("pageInfo",page);

        return "list";
    }

    /**
     * 分页查员工数据，json对象
     * @return
     */
    @ResponseBody
    @RequestMapping("/emps3")
    public Msg getEmps3(@RequestParam(value = "pn",defaultValue = "1")Integer pn){
        //引入PageHelper分页插件
        PageHelper.startPage(pn,5);//第pn页（页码为pn），每页5条数据

        List<Employee> emps = employeeService.getAll();
        //使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了
        //封装了详细的分页信息，包括有我们查询的数据,传入连续显示的页数
        PageInfo page=new PageInfo(emps,5);
        return Msg.success().add("pageInfo",page);
    }

    //json测试
    @RequestMapping("/test")
    @ResponseBody
    public Department test(){
        return new Department(1,"a");
    }

    //json测试
    @ResponseBody
    @RequestMapping("/emps2")
    public PageInfo getEmps(@RequestParam(value = "pn",defaultValue = "1")Integer pn){
        //引入PageHelper分页插件
        PageHelper.startPage(pn,5);//第pn页（页码为pn），每页5条数据

        List<Employee> emps = employeeService.getAll();
        //使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了
        //封装了详细的分页信息，包括有我们查询的数据,传入连续显示的页数
        PageInfo page=new PageInfo(emps,5);
        return page;
    }


}
