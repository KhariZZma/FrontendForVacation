package com.kopyshov.frontendforvacation;

import com.kopyshov.frontendforvacation.controllers.DivisionSettingsController;
import com.kopyshov.frontendforvacation.controllers.IVacationController;
import com.kopyshov.frontendforvacation.controllers.IndexController;
import com.kopyshov.frontendforvacation.controllers.SignformController;
import org.thymeleaf.web.IWebRequest;

import java.util.HashMap;
import java.util.Map;

public class ControllerMappings {
    private static final Map<String, IVacationController> controllersByURL;

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
