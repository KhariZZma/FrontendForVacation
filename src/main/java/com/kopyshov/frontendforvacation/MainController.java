package com.kopyshov.frontendforvacation;

import java.io.*;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

public class MainController implements IGTVGController {

    @Override
    public void process(IWebExchange webExchange, ITemplateEngine templateEngine, Writer writer) {
        WebContext ctx = new WebContext(webExchange, webExchange.getLocale());
        templateEngine.process("index", ctx, writer);
    }
}
