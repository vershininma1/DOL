package com.dol.servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import static com.dol.Constants.*;
import org.apache.log4j.Logger;

@WebServlet("/logout")
/**
 * класс, отвечающий за обработку выхода пользователя
 */
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = -7334062042248346070L;
    private static final Logger log = Logger.getLogger(LogoutServlet.class);
    private static final String CONST_error1="Пользователь вышел.";
    @Override
    /**
     * обработка выхода пользователя
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info(CONST_error1);
        resp.setContentType(CONST_content_type);
        req.setCharacterEncoding(CONST_character_encoding);
        HttpSession session=req.getSession();
        session.setAttribute(CONST_current_user,null);
        resp.sendRedirect(CONST_login);

    }
}