package com.example.stream;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RequestMapping("/employee")
@RestController  //send response...accept request
public class Stream {
    ArrayList<Employee> array=new ArrayList<>(100);

    @GetMapping("/fill") //to be fastttttt :)
    public ArrayList<Employee > fillArrayList()
    {
        array.add(new Employee("yazan",1));
        array.add(new Employee("sami",2));
        array.add(new Employee("yaman",9));
        array.add(new Employee("bara",4));
        array.add(new Employee("hasan",6));
        return array;
    }
    @PostMapping
    public Employee adddEmployee(@RequestBody  Employee emp)
    {
        array.add(new Employee(emp.getName(),emp.getId()));
        return emp;
    }
    @GetMapping
    public ArrayList<Employee > getEmployee()
    {
        return array;
    }
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id)
    {
         return array.stream().filter(i -> (i.getId()==id)).findFirst().orElse(null);
    }
    @PutMapping("/{id}")
    public ArrayList<Employee> updateEmployee(@PathVariable int id, @RequestBody  Employee employee)
    {
         array.stream().filter(i -> (i.getId()==id)).findFirst().ifPresent(e->array.set(array.indexOf(e),employee));
          return array;
    }
    @DeleteMapping ("/{id}")
    public ArrayList<Employee> deleteEmployee(@PathVariable int id)
    {
        array.stream().filter(i -> (i.getId()==id)).findFirst().ifPresent(e->array.remove(e));
        return array;
    }
    @GetMapping ("/sort-id")
    public List<Employee> sortEmployeeById()
    {
      return array.stream().sorted(Comparator.comparingInt(Employee::getId)).collect(Collectors.toList());

    }
    @GetMapping ("/sort-name")
    public List<Employee> sortEmployeeByName()
    {
        return array.stream().sorted(Comparator.comparing(Employee::getName)).collect(Collectors.toList());
    }

    @GetMapping("/map")
    public Map<Integer, Employee> listEmployeeAsMap()
    {
        return array.stream().collect(Collectors.toMap(Employee::getId,employee -> employee));
    }

    @GetMapping("/names")
    public List<String> listEmployeeNames()
    {
        return array.stream().map(Employee::getName).collect(Collectors.toList());
    }
    @GetMapping("/max")
    public  Employee maxId()
    {
      return array.stream().max(Comparator.comparingInt(Employee::getId)).orElse(null);
    }
}
