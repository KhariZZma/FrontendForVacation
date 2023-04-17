package com.kopyshov.frontendforvacation;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;


public class DivisionSettingsController implements IVacationController {

    @Override
    public void process(IWebExchange webExchange, ITemplateEngine templateEngine, Writer writer) throws Exception {
        if(webExchange.getRequest().getMethod().equals("POST")) {
            handlePost(webExchange);
            handleGet(webExchange, templateEngine, writer);
        }
        if (webExchange.getRequest().getMethod().equals("GET")) {
            handleGet(webExchange, templateEngine, writer);
        }
    }

    private void handlePost(IWebExchange webExchange) {
        String id;
        String surname;
        String firstName;
        String patronymic;
        if (webExchange.getRequest().containsParameter("employee-id")) {
            System.out.println("Есть ИД");
            id = (String) webExchange.getRequest().getParameterValue("employee-id");
            surname = (String) webExchange.getRequest().getParameterValue("surname");
            firstName = (String) webExchange.getRequest().getParameterValue("first-name");
            patronymic = (String) webExchange.getRequest().getParameterValue("patronymic");
            HashMap<String, Employee> employees = ControllerMappings.getEmployees();
            employees.replace(id, new Employee(id, surname, firstName, patronymic));
        } else {
            System.out.println("Нет ИД");
            id = ControllerMappings.generateId();
            surname = (String) webExchange.getRequest().getParameterValue("surname");
            firstName = (String) webExchange.getRequest().getParameterValue("first-name");
            patronymic = (String) webExchange.getRequest().getParameterValue("patronymic");
            HashMap<String, Employee> employees = ControllerMappings.getEmployees();
            employees.put(id, new Employee(id, surname, firstName, patronymic));
        }
    }

    private void handleGet(IWebExchange webExchange, ITemplateEngine templateEngine, Writer writer) {
        WebContext ctx = new WebContext(webExchange, webExchange.getLocale());
        ArrayList<Employee> employees = new ArrayList<>(ControllerMappings.getEmployees().values());
        ctx.setVariable("employees", employees);
        templateEngine.process("settings", ctx, writer);
    }
}
