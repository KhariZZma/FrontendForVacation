package com.kopyshov.frontendforvacation;

import com.kopyshov.frontendforvacation.controllers.IVacationController;
import com.kopyshov.frontendforvacation.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import java.io.Writer;
import java.util.List;

public class WorkViewController implements IVacationController {
    private static final String ALL_EMPLOYEES = "from Employee";
    @Override
    public void process(IWebExchange webExchange, ITemplateEngine templateEngine, Writer writer) throws Exception {
        WebContext ctx = new WebContext(webExchange, webExchange.getLocale());
        List employees;

        try(Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            employees = session.createQuery(ALL_EMPLOYEES).list();
            tx.commit();
        }

        ctx.setVariable("employees", employees);
        templateEngine.process("workview", ctx, writer);
    }
}
