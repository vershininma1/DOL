package com.dol.servlet;

import com.dol.bean.ContractBean;
import com.dol.bean.StatusBean;
import com.dol.entity.Contract;
import com.dol.entity.Status;
import org.apache.log4j.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import static com.dol.Constants.*;

@WebServlet("/list")
/**
 * обработка списков контрактов
 */
public class ListServlet extends HttpServlet {
    private static final long serialVersionUID = -2362587850453345803L;
    @EJB
    private ContractBean contractBean;
    @EJB
    private StatusBean statusBean;
    private static final Logger log = Logger.getLogger(ListServlet.class);
    private ResourceBundle mybundle = ResourceBundle.getBundle(CONST_Bundle_Ru);
     private static final String CONST_searchname = "searchname";
    private static final String CONST_searchsetname = "searchsetname";
    private static final String CONST_searchstatusname = "searchstatusname";
    private static final String CONST_enddate = "enddate";
    private static final String CONST_startdate = "startdate";
    private static final String CONST_statuses3 = "statuses3";
    private static final String CONST_contracts = "contracts";
    private static final String CONST_WEB_INF_list = "/WEB-INF/list.jsp";
    private static final String CONST_dateFormat = "yyyy-MM-dd";
    private static final String CONST_EndDate1 = "EndDate1";
    private static final String CONST_StartDate1 = "StartDate1";
    private static final String CONST_SearchName1 = "SearchName1";
    private static final String CONST_SearchSetName1 = "SearchSetName1";
    private static final String CONST_searchStatusName1 = "searchStatusName1";
    private static final String CONST_redirect = "list?searchname=%s&searchsetname=%s&searchstatusname=%s&startdate=%s&enddate=%s";
    private static final String CONST_error1 = "Данная ссылка не может быть преобразована в корректную";
    private static final String  CONST_error2="были заданы некорректные данные поиска";
    private static final String  CONST_error3="Преобразование строки к Int невозможно,введена некорректная строка \"%s\".";
    private static final String  CONST_error4="Преобразование даты невозможно,введена некорректная дата \"%s\".";
    private static final String  CONST_error5="Пользователь нажал на кнопку поиска";
    @Override
    /**
     * обрабатываем страницу со списком контрактов
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
            req.setAttribute(CONST_ourError,CONST_empty);
            String searchName = req.getParameter(CONST_searchname);
            String searchSetName = req.getParameter(CONST_searchsetname);
            String searchStatusId = req.getParameter(CONST_searchstatusname);
            Date startDate = parseDate(req.getParameter(CONST_startdate),req,resp);
            Date endDate = parseDate(req.getParameter(CONST_enddate),req,resp);
            searchStatusId= checkStatusID(searchStatusId,req,resp);
            List<Status> ourStatus = statusBean.getAll();
            req.setAttribute(CONST_statuses3, ourStatus);
            List<Contract> ourContract = contractBean.getSearch(searchName, searchSetName, searchStatusId, startDate, endDate);
            req.setAttribute(CONST_contracts, ourContract);
            req.getRequestDispatcher(CONST_WEB_INF_list).forward(req, resp);
        }

        } catch (Throwable e) {
            log.error(CONST_error2);
            req.setAttribute(CONST_ourError,CONST_error2);


        }
    }
    /**
     * проверяет возможность преобразования строки к числу
     * @param searchStatusId
     * @param req
     * @param resp
     */
    private String checkStatusID(String searchStatusId,HttpServletRequest req,HttpServletResponse resp){
      String  oursearchStatusId=searchStatusId;
        if(!(searchStatusId.equals(CONST_empty))) {
            try {
                int i = Integer.parseInt(searchStatusId);
            } catch (NumberFormatException  e) {

               String ourError1=String.format(CONST_error3, searchStatusId);
                log.error(ourError1);
                req.setAttribute(CONST_ourError,ourError1);
                oursearchStatusId="";
                }
        }
        return oursearchStatusId;

    }

    /**
     *преобразует строку в дату
     * @param ourDate
     * @return Date
     */
    private Date parseDate( String ourDate,HttpServletRequest req,HttpServletResponse resp) {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern(CONST_dateFormat);
        Date tempDate=null;
        if(ourDate!=null&&!(ourDate.equals(CONST_empty))) {
            try {
                tempDate=format.parse( ourDate);
            } catch (ParseException e) {
                String ourError1=String.format(CONST_error4,ourDate);
                log.error(ourError1);
                req.setAttribute(CONST_ourError,ourError1);
                          }
        }
        return tempDate;
    }
    @Override
    /**
     * отправляет форму с запросом поиска
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        resp.setContentType(CONST_content_type);
        req.setCharacterEncoding(CONST_character_encoding);
        log.info(CONST_error5);

        try{
            String endDate =req.getParameter(CONST_EndDate1);
            String startDate =req.getParameter(CONST_StartDate1);
            String searchName = req.getParameter(CONST_SearchName1);
            String  searchSetName = req.getParameter(CONST_SearchSetName1);

            String   searchStatusName= req.getParameter(CONST_searchStatusName1);
            if(searchStatusName==null){searchStatusName=CONST_empty;}
            resp.sendRedirect(String.format(CONST_redirect, URLEncoder.encode(searchName, CONST_character_encoding), URLEncoder.encode(searchSetName, CONST_character_encoding),URLEncoder.encode(searchStatusName,CONST_character_encoding),startDate,endDate));
        } catch (Throwable e) {
            log.error(CONST_error1);
            req.setAttribute(CONST_ourError,CONST_error1);
        }
    }

    }



