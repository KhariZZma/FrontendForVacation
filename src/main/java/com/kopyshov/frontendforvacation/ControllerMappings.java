package com.kopyshov.frontendforvacation;

import org.thymeleaf.web.IWebRequest;

import java.util.HashMap;
import java.util.Map;

public class ControllerMappings {

    private static Map<String, IGTVGController> controllersByURL;


    static {
        controllersByURL = new HashMap<>();
        controllersByURL.put("/", new MainController());
        controllersByURL.put("/create", new DivisionController());
    }



    public static IGTVGController resolveControllerForRequest(final IWebRequest request) {
        final String path = request.getPathWithinApplication();
        return controllersByURL.get(path);
    }

    private ControllerMappings() {
        super();
    }
}
