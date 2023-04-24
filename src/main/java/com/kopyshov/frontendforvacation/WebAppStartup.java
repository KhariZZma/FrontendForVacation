package com.kopyshov.frontendforvacation;

import com.kopyshov.frontendforvacation.model.Employee;
import com.kopyshov.frontendforvacation.model.Vacation;
import com.kopyshov.frontendforvacation.utils.HibernateSessionFactory;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;

@WebListener
public class WebAppStartup implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    public WebAppStartup() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        testEmployeesInsert();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
    }

    private static void testEmployeesInsert() {
        try(Session session = HibernateSessionFactory.getSessionFactory().openSession()){
            Transaction tx = session.beginTransaction();

            Vacation vac1 = new Vacation();
            vac1.setStartDate(LocalDate.of(2022, 1, 1));
            vac1.setFinishDate(LocalDate.of(2022, 1, 31));
            session.save(vac1);

            Employee emp1 = new Employee();
            emp1.setSurname("Surname0");
            emp1.setFirstName("Name0");
            emp1.setPatronymic("Patronymic0");
            emp1.getVacations().add(vac1);

            Employee emp2 = new Employee();
            emp2.setSurname("Surname1");
            emp2.setFirstName("Name1");
            emp2.setPatronymic("Patronymic1");

            session.save(emp1);
            session.save(emp2);
            session.flush();

            tx.commit();
        }
    }
}
