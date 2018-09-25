package com.dol.servlet;

import com.dol.bean.ContractBean;
import com.dol.entity.Contract;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import static com.dol.Constants.*;
import org.apache.log4j.Logger;
@MultipartConfig
@WebServlet("/view")
/**
 * класс, отвечающий за страницу, показывающую контракт
 */
public class ViewServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(ViewServlet.class);
    private static final long serialVersionUID = 8508580851464661144L;
    @EJB
    private ContractBean contractBean;
    private static final String CONST_redirect="list?searchname=&searchsetname=&searchstatusname=&startdate=&enddate=";
    private static final String CONST_WEB_INF_view="/WEB-INF/view.jsp";
    private static final String CONST_contractnumber="contractnumber";
    private static final String CONST_contract="contract";
    private static final String CONST_error1="Ошибка с получением данных из БД";
    private static final String CONST_error2="Переход со страницы просмотра контрактов на страницу со всеми контрактами";
    private static final String CONST_error3="Зашли на страницу просмотра контракта";
    @Override
    /**
     * показывает контракт
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
        resp.setContentType(CONST_content_type);
        req.setCharacterEncoding(CONST_character_encoding);
        HttpSession session = req.getSession();
        String current_user = (String) session.getAttribute(CONST_current_user);
        if (current_user == null) {
            resp.sendRedirect(CONST_login);
        } else {
            int id = Integer.valueOf(req.getParameter(CONST_contractnumber));
            Contract contract = contractBean.get(id);

            req.setAttribute(CONST_contract, contract);
            log.info(CONST_error3);
            req.getRequestDispatcher(CONST_WEB_INF_view).forward(req, resp);
        }
        } catch (Throwable e) {
            log.error(CONST_error1);
        }
    }
    @Override
    /**
     * перенаправление на список контрактов
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(CONST_content_type);
        req.setCharacterEncoding(CONST_character_encoding);
        log.info(CONST_error2);
        resp.sendRedirect(CONST_redirect);
    }
}
