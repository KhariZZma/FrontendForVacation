package com.kopyshov.frontendforvacation;

import org.thymeleaf.web.IWebRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ControllerMappings {
    private static Map<String, IVacationController> controllersByURL;
    private static ArrayList<Employee> employees;

    static {
        controllersByURL = new HashMap<>();
        controllersByURL.put("/", new IndexController());
        controllersByURL.put("/create", new DivisionSettingsController());
        controllersByURL.put("/division", new DivisionViewController());

        createDatabase();
    }



    public static IVacationController resolveControllerForRequest(final IWebRequest request) {
        final String path = request.getPathWithinApplication();
        return controllersByURL.get(path);
    }

    private ControllerMappings() {
        super();
    }

    private static void createDatabase() {
        employees = new ArrayList<>();

        Employee emp1 = new Employee("Петров", "Дмитрий", "Дмитриевич", "полный");
        Employee emp2 = new Employee("Иванов", "Василий", "Васильевич", "ограничен");
        Employee emp3 = new Employee("Сидоров", "Алексей", "Алексеевич", "ограничен");
        Employee emp4 = new Employee("Петрова", "Дарья", "Дмитриевна", "ограничен");
        Employee emp5 = new Employee("Иванова", "Екатерина", "Васильевна", "ограничен");
        Employee emp6 = new Employee("Сидорова", "Юлия", "Алексеевна", "ограничен");

        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);
        employees.add(emp4);
        employees.add(emp5);
        employees.add(emp6);
    }

    public static ArrayList<Employee> getEmployees() {
        return employees;
    }
}
