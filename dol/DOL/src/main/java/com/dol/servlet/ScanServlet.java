package com.dol.servlet;

import com.dol.bean.ContractBean;
import com.dol.entity.Contract;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import static com.dol.Constants.*;
import org.apache.log4j.Logger;
@WebServlet("/scan")
/**
 * класс, отвечающий за загрузку картинки
 */
public class ScanServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(ScanServlet.class);
    private static final long serialVersionUID = 6435721723036838702L;
    @EJB
    private ContractBean contractBean;
    private static final String CONST_im="im";
    private static final String CONST_image_jpg="image/jpg";
    private static final String CONST_scan_jsp="/scan.jsp";
    private static final String CONST_error1="Загрузка картинки";
    @Override
    /**
     * загрузка картинки
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
        resp.setContentType(CONST_content_type);
        req.setCharacterEncoding(CONST_character_encoding);
        HttpSession session = req.getSession();
        String current_user = (String) session.getAttribute(CONST_current_user);
        if (current_user == null) {
            resp.sendRedirect(CONST_login);
        } else {
            int id = Integer.valueOf(req.getParameter(CONST_im));
            Contract contract = contractBean.get(id);
            byte[] imageData = contract.getScan();
            resp.setContentType(CONST_image_jpg);
            resp.getOutputStream().write(imageData);
            resp.getOutputStream().flush();
            resp.getOutputStream().close();
            req.getRequestDispatcher(CONST_scan_jsp).forward(req, resp);
        }
        } catch (Throwable e) {
            log.info(CONST_error1);

        }
    }
}