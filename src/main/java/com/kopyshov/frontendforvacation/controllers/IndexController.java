package com.kopyshov.frontendforvacation.controllers;

import java.io.*;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

public class IndexController implements IVacationController {
    @Override
    public void process(IWebExchange webExchange, ITemplateEngine templateEngine, Writer writer) {
        WebContext ctx = new WebContext(webExchange, webExchange.getLocale());
        templateEngine.process("index", ctx, writer);
    }
}
