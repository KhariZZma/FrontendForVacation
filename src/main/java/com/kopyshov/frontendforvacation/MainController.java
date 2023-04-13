package com.kopyshov.frontendforvacation;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

@WebServlet("/")
public class MainServlet extends HttpServlet implements IGTVGController {
    public void init() {

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            TemplateEngine engine = new TemplateEngine();

            IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext())
                    .buildExchange(request, response);
            this.process(webExchange, engine, response.getWriter());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void destroy() {
    }

    @Override
    public void process(IWebExchange webExchange, ITemplateEngine templateEngine, Writer writer) {
        WebContext ctx = new WebContext(webExchange, webExchange.getLocale());
        templateEngine.process("index", ctx, writer);
    }
}
