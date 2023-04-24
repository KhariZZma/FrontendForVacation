package com.kopyshov.frontendforvacation.controllers;

import com.kopyshov.frontendforvacation.model.Employee;
import com.kopyshov.frontendforvacation.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import java.io.Writer;
import java.util.List;


public class DivisionSettingsController implements IVacationController {
    private static final String ALL_EMPLOYEES = "from Employee";
    private WebContext ctx;
    @Override
    public void process(IWebExchange webExchange, ITemplateEngine templateEngine, Writer writer) {
        ctx = new WebContext(webExchange, webExchange.getLocale());
        if(webExchange.getRequest().getMethod().equals("POST")) {
            handlePost(webExchange);
            handleGet(templateEngine, writer);
        }
        if (webExchange.getRequest().getMethod().equals("GET")) {
            handleGet(templateEngine, writer);
        }
    }

    private void handlePost(IWebExchange webExchange) {
        String id;
        String surname;
        String firstName;
        String patronymic;
        if (webExchange.getRequest().containsParameter("employee-id")) {
            id = webExchange.getRequest().getParameterValue("employee-id");
            surname = webExchange.getRequest().getParameterValue("surname");
            firstName = webExchange.getRequest().getParameterValue("first-name");
            patronymic = webExchange.getRequest().getParameterValue("patronymic");

            try(Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
                Transaction tx = session.beginTransaction();
                session.update(new Employee(Integer.parseInt(id), surname, firstName, patronymic));
                tx.commit();
            }
        } else {
            surname = webExchange.getRequest().getParameterValue("surname");
            firstName = webExchange.getRequest().getParameterValue("first-name");
            patronymic = webExchange.getRequest().getParameterValue("patronymic");
            Employee employee = new Employee();
            employee.setSurname(surname);
            employee.setFirstName(firstName);
            employee.setPatronymic(patronymic);
            try(Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
                Transaction tx = session.beginTransaction();
                session.save(employee);
                tx.commit();
            }
        }
    }

    private void handleGet(ITemplateEngine templateEngine, Writer writer) {
        List employees;

        try(Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            employees = session.createQuery(ALL_EMPLOYEES).list();
            tx.commit();
        }

        ctx.setVariable("employees", employees);
        templateEngine.process("settings", ctx, writer);
    }

}
