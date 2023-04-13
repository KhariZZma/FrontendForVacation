package com.kopyshov.frontendforvacation;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.IWebApplication;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.IWebRequest;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.io.Writer;

@WebServlet(name = "SecondaryServlet", value = "/try")
public class SecondaryServlet extends HttpServlet {

    private ITemplateEngine templateEngine;
    private JakartaServletWebApplication application;

    @Override
    public void init() {
        this.application = JakartaServletWebApplication.buildApplication(getServletContext());
        this.templateEngine = buildTemplateEngine(this.application);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            final IWebExchange webExchange = this.application.buildExchange(request, response);
            final IWebRequest webRequest = webExchange.getRequest();

            if (webRequest.getPathWithinApplication().startsWith("/css") ||
                    webRequest.getPathWithinApplication().startsWith("/images") ||
                    webRequest.getPathWithinApplication().startsWith("/favicon")) {
                return;
            }


            response.setContentType("text/html;charset=UTF-8");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);

            final Writer writer = response.getWriter();

            WebContext ctx = new WebContext(webExchange, webExchange.getLocale());
            templateEngine.process("try", ctx, writer);


        } catch (final Exception e) {
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (final IOException ignored) {
                // Just ignore this
            }
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    private static ITemplateEngine buildTemplateEngine(final IWebApplication application) {

        final WebApplicationTemplateResolver templateResolver = new WebApplicationTemplateResolver(application);

        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setCacheTTLMs(3600000L);
        templateResolver.setCacheable(true);

        final TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        return templateEngine;
    }
}
