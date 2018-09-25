package com.dol.servlet;

import com.dol.bean.ContractBean;
import com.dol.bean.SetBean;
import com.dol.entity.Contract;
import com.dol.entity.MySet;
import org.apache.commons.io.IOUtils;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.dol.Constants.*;
import org.apache.log4j.Logger;
@MultipartConfig
@WebServlet("/add")
/**
 * обработка добавления контракта
 */
public class AddAndEditContractServlet extends HttpServlet {
    private static final long serialVersionUID = 4655530365384318722L;
    @EJB
    private ContractBean contractBean;
    @EJB
    private SetBean setBean;
    private static final Logger log = Logger.getLogger(AddAndEditContractServlet.class);
    private static final String CONST_mysets="mysets7";
    private static final String CONST_WEB_INF_add="/WEB-INF/add.jsp";
    private static final String CONST_passportID="passportID";
    private static final String CONST_SetIdParam="SetIdParam";
    private static final String CONST_data="data";
    private static final String CONST_list="list?searchname=&searchsetname=&searchstatusname=&startdate=&enddate=";
    private static final String CONST_error1="Данные введены некорректно";
    private static final String CONST_error2="Проблема с получением списка комплектов";
    private static final String CONST_error3="Пользователь зашел на страницу добавления контракта";
    private static final String CONST_error4="Данные введены,добавляем контракт";
    private static final String CONST_addError="addError";
    private static final String CONST_addUserLogin="addUserLogin";
    private static final String CONST_addPassportID="addPassportID";
    private static final String CONST_addSetIdParam="addSetIdParam";
    private static final String CONST_add="add";
    private static final String CONST_1="1";
    private static final String CONST_reg= "[0-9]{10}";

    @Override
    /**
     * обработка страницы добавления контракта
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
            log.info(CONST_error3);
            List<MySet> ourSet = setBean.getFree(current_user);
            req.setAttribute(CONST_mysets, ourSet);
            req.getRequestDispatcher(CONST_WEB_INF_add).forward(req, resp);
            session.setAttribute(CONST_addError,CONST_empty);//убираем ошибку на странице добавления контракта
            session.setAttribute(CONST_addUserLogin,CONST_empty);
            session.setAttribute(CONST_addPassportID,CONST_empty);
            session.setAttribute(CONST_addSetIdParam,CONST_empty);
        }
} catch (Throwable e) {
    log.error(CONST_error2);
}

    }
    @Override
    /**
     * обработка формы получения контракта
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            log.info(CONST_error4);
        resp.setContentType(CONST_content_type);
        req.setCharacterEncoding(CONST_character_encoding);
        String userLogin = req.getParameter(CONST_userLogin);
        String passportID = req.getParameter(CONST_passportID);
        String ourSetIdParam=req.getParameter( CONST_SetIdParam);
        int setIdParam = Integer.valueOf(ourSetIdParam);
        MySet mySet=setBean.get(setIdParam);
        Part image = req.getPart(CONST_data);
             InputStream is=image.getInputStream();
        byte[] scan = IOUtils.toByteArray(is);
        //проверяем валидность
        if(checkWithRegExp(passportID)&&(userLogin!=null&&!userLogin.equals(CONST_empty))&&scan.length!=0) {
            contractBean.add(new Contract(userLogin, passportID, mySet, scan));
        setBean.makeBusy(mySet);
        resp.sendRedirect(CONST_list);
        }else{log.error(CONST_error1);
//запоминаем введенные данные и передаем сообщение об ошибке
            HttpSession session = req.getSession();
            session.setAttribute(CONST_addError,CONST_1);
            session.setAttribute(CONST_addUserLogin,userLogin);
            session.setAttribute(CONST_addPassportID,passportID);
            session.setAttribute(CONST_addSetIdParam,ourSetIdParam);
            resp.sendRedirect(CONST_add);
        }

        } catch (Throwable e) {
            log.error(CONST_error1);
            req.setAttribute(CONST_ourError,CONST_error1);
        }
    }




    public static boolean checkWithRegExp(String ourPass){
        Pattern p = Pattern.compile(CONST_reg);
        Matcher m = p.matcher(ourPass);
        return m.matches();
    }

}
