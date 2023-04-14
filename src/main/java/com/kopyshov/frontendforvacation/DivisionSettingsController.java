package com.kopyshov.frontendforvacation;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import java.io.Writer;


public class DivisionSettingsController implements IVacationController {

    @Override
    public void process(IWebExchange webExchange, ITemplateEngine templateEngine, Writer writer) throws Exception {
        WebContext ctx = new WebContext(webExchange, webExchange.getLocale());
        ctx.setVariable("employees", ControllerMappings.getEmployees());
        templateEngine.process("settings", ctx, writer);
    }
}
