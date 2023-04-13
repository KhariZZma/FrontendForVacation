package com.kopyshov.frontendforvacation;

import org.thymeleaf.web.IWebRequest;

import java.util.HashMap;
import java.util.Map;

public class ControllerMappings {
    private static Map<String, IVacationController> controllersByURL;

    static {
        controllersByURL = new HashMap<>();
        controllersByURL.put("/", new IndexController());
        controllersByURL.put("/create", new DivisionSettingsController());
        controllersByURL.put("/division", new DivisionViewController());
    }

    public static IVacationController resolveControllerForRequest(final IWebRequest request) {
        final String path = request.getPathWithinApplication();
        return controllersByURL.get(path);
    }

    private ControllerMappings() {
        super();
    }
}
