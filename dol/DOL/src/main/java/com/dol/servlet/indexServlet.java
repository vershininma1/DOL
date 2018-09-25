package com.dol.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import java.io.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import static com.dol.Constants.*;

@MultipartConfig
@WebServlet("")
/**
 * переадресация с начальной страницы
 */
public class indexServlet extends HttpServlet {
    private static final long serialVersionUID = 1024701662477747310L;

    @Override
    /**
     * переадресация с начальной страницы
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          resp.sendRedirect(CONST_login);
    }
}
