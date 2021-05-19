package ru.paalse;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/first-servlet")
public class FirstServlet implements Servlet {

    private ServletConfig config;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {     // Инициализация приложения
        this.config = servletConfig;

    }

    @Override
    public ServletConfig getServletConfig() { // Получение конфига
        return this.config;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException { // Обрадотка запросов

        servletResponse.getWriter().println("<h1>Привет мир!</h1>");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {     // Завершение работы приложения

    }
}
