package com.nhnacademy.hello;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@Slf4j
public class LoginServlet extends HttpServlet {
    private String initParamId;
    private String initParamPwd;

    @Override
    public void init(ServletConfig config) throws ServletException {

        initParamId = config.getInitParameter("id");
        initParamPwd = config.getInitParameter("pwd");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("id") != null) {
            // 로그인되어 있으면 원래 요청 페이지로
            String redirect = (String) session.getAttribute("redirectAfterLogin");
            if (redirect != null) {
                session.removeAttribute("redirectAfterLogin");
                resp.sendRedirect(req.getContextPath() + redirect);
                return;
            }
        }
        resp.sendRedirect(req.getContextPath() + "/login.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        String pwd = req.getParameter("pwd");

        if (initParamId.equals(id) && initParamPwd.equals(pwd)) {
            //session 있으면 가져오고 없으면 생성
            HttpSession session = req.getSession();
            session.setAttribute("id", id);

            String originalUrl = (String) session.getAttribute("redirectAfterLogin");

            if (originalUrl != null) {
                session.removeAttribute("redirectAfterLogin");
                resp.sendRedirect(req.getContextPath() + originalUrl);
            } else {
                resp.sendRedirect(req.getContextPath() + "/login.html");
            }

        } else {
            log.error("아이디/패스워드가 일치하지 않습니다.");
            resp.sendRedirect("/login.html");
//            RequestDispatcher rd = req.getRequestDispatcher("/login.html");
//            rd.forward(req, resp);
//            log.error("id:{}", id);
        }
    }
}