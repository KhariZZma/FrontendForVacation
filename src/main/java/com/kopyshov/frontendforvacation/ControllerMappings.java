package com.kopyshov.frontendforvacation;

import com.kopyshov.frontendforvacation.controllers.DivisionSettingsController;
import com.kopyshov.frontendforvacation.controllers.IVacationController;
import com.kopyshov.frontendforvacation.controllers.IndexController;
import com.kopyshov.frontendforvacation.controllers.SignformController;
import com.kopyshov.frontendforvacation.model.Employee;
import com.kopyshov.frontendforvacation.model.Vacation;
import com.kopyshov.frontendforvacation.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.thymeleaf.web.IWebRequest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ControllerMappings {
    private static Map<String, IVacationController> controllersByURL;

    static {
        controllersByURL = new HashMap<>();
        controllersByURL.put("/", new IndexController());
        controllersByURL.put("/create", new DivisionSettingsController());
        controllersByURL.put("/workview", new WorkViewController());
        controllersByURL.put("/signform", new SignformController());


    }



    public static IVacationController resolveControllerForRequest(final IWebRequest request) {
        final String path = request.getPathWithinApplication();
        return controllersByURL.get(path);
    }

    private ControllerMappings() {
        super();
    }


}
