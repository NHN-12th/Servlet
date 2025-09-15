package com.nhnacademy.hello;

import jakarta.servlet.http.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;

@Slf4j
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);

        if (Objects.nonNull(session)) {
            session.invalidate();
        }

        Cookie cookie = CookieUtils.getCookie(req, "JSESSIONID");
        if (Objects.nonNull(cookie)) {
            cookie.setValue("");
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
        }
        resp.sendRedirect("/login");
    }
}
