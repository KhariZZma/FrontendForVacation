package com.kopyshov.frontendforvacation;

import org.thymeleaf.web.IWebRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ControllerMappings {
    private static Map<String, IVacationController> controllersByURL;
    private static HashMap<String, Employee> employees;
    private static Integer idGenerator;

    static {
        idGenerator = 7;
        controllersByURL = new HashMap<>();
        controllersByURL.put("/", new IndexController());
        controllersByURL.put("/create", new DivisionSettingsController());
        controllersByURL.put("/division", new DivisionViewController());
        controllersByURL.put("/signform", new SignformController());

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
        employees = new HashMap<>();

        Employee emp1 = new Employee("1", "Петров", "Дмитрий", "Дмитриевич");
        Employee emp2 = new Employee("2", "Иванов", "Василий", "Васильевич");
        Employee emp3 = new Employee("3", "Сидоров", "Алексей", "Алексеевич");
        Employee emp4 = new Employee("4","Петрова", "Дарья", "Дмитриевна");
        Employee emp5 = new Employee("5","Иванова", "Екатерина", "Васильевна");
        Employee emp6 = new Employee("6","Сидорова", "Юлия", "Алексеевна");

        employees.put("1", emp1);
        employees.put("2", emp2);
        employees.put("3", emp3);
        employees.put("4", emp4);
        employees.put("5", emp5);
        employees.put("6", emp6);
    }

    public static HashMap<String, Employee> getEmployees() {
        return employees;
    }

    public static String generateId() {
        idGenerator++;
        return idGenerator.toString();
    }
}
