package top.ambtwill.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Employee {

    private Integer empId;

    // @Pattern
    private String empName;

    private String gender;

    // @Pattern(regexp="")
    private String email;

    //要查出部门的名字
    private Department department;

    // @Email
    private Integer dId;



    public Employee(Integer empId, String empName, String gender, String email, Integer dId) {
        this.empId = empId;
        this.empName = empName;
        this.gender = gender;
        this.email = email;
        this.dId = dId;
    }
}