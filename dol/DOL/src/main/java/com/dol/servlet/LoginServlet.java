package com.dol.servlet;

import com.dol.bean.UserBean;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import static com.dol.Constants.*;
import org.apache.log4j.Logger;
@WebServlet("/login")
/**
 * класс, отвечающий за обработку страницы логина
 */
public class LoginServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(LoginServlet.class);
    private static final long serialVersionUID = -6299732512796565121L;
    @EJB
    private UserBean userBean;
    private static final String CONST_login_jsp="/login.jsp";
    private static final String CONST_redirect="list?searchname=&searchsetname=&searchstatusname=&startdate=&enddate=";
    private static final String CONST_login_err="login?err=1";
    private static final String CONST_error1="Алгоритм шифрования не найден";
    @Override
    /**
     * обработка страницы логина
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(CONST_content_type);
        req.setCharacterEncoding(CONST_character_encoding);
        req.getRequestDispatcher(CONST_login_jsp).forward(req, resp);
    }
    @Override
    /**
     * обработка формы логина
     */
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(CONST_content_type);
        req.setCharacterEncoding(CONST_character_encoding);
        HttpSession session=req.getSession();
            String login = req.getParameter(CONST_login);
            String pass = req.getParameter(CONST_pass);
        try {
            if(userBean.check(login, pass)) {
                // тут логин прошел
                session.setAttribute(CONST_current_user,login);
                resp.sendRedirect(CONST_redirect);
            }else {
//если логин не прошел
                resp.sendRedirect(CONST_login_err);
            }
        } catch (NoSuchAlgorithmException e) {
            log.error(CONST_error1);
        }
    }
}