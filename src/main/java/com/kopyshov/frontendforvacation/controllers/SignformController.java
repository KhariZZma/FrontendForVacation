package com.kopyshov.frontendforvacation.controllers;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import java.io.Writer;

public class SignformController implements IVacationController {
    @Override
    public void process(IWebExchange webExchange, ITemplateEngine templateEngine, Writer writer) {
        WebContext ctx = new WebContext(webExchange, webExchange.getLocale());
        templateEngine.process("signform", ctx, writer);
    }
}
