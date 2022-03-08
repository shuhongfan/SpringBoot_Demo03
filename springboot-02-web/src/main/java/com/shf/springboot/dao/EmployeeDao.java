package com.shf.springboot.dao;

import com.shf.springboot.pojo.Department;
import com.shf.springboot.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {
    //    模拟数据库中的数据
    private static Map<Integer, Employee> employees=null;

    @Autowired
    private DepartmentDao departEmployeeDao;

    static {
        employees = new HashMap<Integer, Employee>(); // 创建一个部门表
        employees.put(101,new Employee(101,"AA","32242@qq.com",1,new Department(101,"教学部"),new Date()));
        employees.put(102,new Employee(102,"AA","32242@qq.com",1,new Department(102,"市场部"),new Date()));
        employees.put(103,new Employee(103,"AA","32242@qq.com",0,new Department(103,"教研部"),new Date()));
        employees.put(104,new Employee(104,"AA","32242@qq.com",0,new Department(104,"运营部"),new Date()));
        employees.put(105,new Employee(105,"AA","32242@qq.com",1,new Department(105,"后勤部"),new Date()));
    }

//    主键自增
    private static Integer initId = 1006;

//    增加一个员工
    public void save(Employee employee){
        if (employee.getId() == null){
            employee.setId(initId++);
        }

        employee.setDepartment(departEmployeeDao.getDepartmentById(employee.getDepartment().getId()));

        employees.put(employee.getId(),employee);
    }

//    查询全部员工信息
    public Collection<Employee> getAllEmployees(){
        return employees.values();
    }

//    通过id查询员工
    public Employee getEmployeeById(Integer id){
        return employees.get(id);
    }

//    删除员工通过id
    public void delete(Integer id){
        employees.remove(id);
    }

}
