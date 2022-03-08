package com.shf.springboot.controller;

import com.shf.springboot.dao.DepartmentDao;
import com.shf.springboot.dao.EmployeeDao;
import com.shf.springboot.pojo.Department;
import com.shf.springboot.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    @RequestMapping("/emps")
    public String list(Model model){
        Collection<Employee> allEmployees = employeeDao.getAllEmployees();
        model.addAttribute("emps",allEmployees);
        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAddPage(Model model){
        Collection<Department> departmentList = departmentDao.getDepartmentList();
        model.addAttribute("departments",departmentList);
        return "emp/add";
    }

    @PostMapping("/emp")
    public String addEmployee(Employee e){
//        添加的操作
        employeeDao.save(e);
        return "redirect:/emps";
    }

    @GetMapping("/emp/{id}")
    public String toUpdateEmp(@PathVariable("id") Integer id, Model model){
//        查出原来的数据
        Employee employeeById = employeeDao.getEmployeeById(id);
        model.addAttribute("emp",employeeById);
        Collection<Department> departmentList = departmentDao.getDepartmentList();
        model.addAttribute("departments",departmentList);
        return "emp/update";
    }

    @PostMapping("/updateEmp")
    public String updateEmployee(Employee e) {
        employeeDao.save(e);
        return "redirect:/emps";
    }

    @RequestMapping("/delemp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }

    @RequestMapping("/user/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/index.html";
    }
}
