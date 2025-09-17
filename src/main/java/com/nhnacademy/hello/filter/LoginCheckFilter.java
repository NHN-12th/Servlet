package com.nhnacademy.hello.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Slf4j
public class LoginCheckFilter implements Filter {
    private final Set<String> excludeUrls = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String urls = filterConfig.getInitParameter("exclude-urls");
        log.error("exclude-urls:{}", urls);
        Arrays.stream(urls.split("\\r?\\n"))
                .map(String::trim)
                .forEach(excludeUrls::add);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String requestUri = req.getRequestURI();

        if (!excludeUrls.contains(requestUri)) {
            HttpSession session = req.getSession(false);

            if (Objects.isNull(session)) { // 세션이 null
                session = req.getSession();
                session.setAttribute("redirectAfterLogin", requestUri);
                resp.sendRedirect(req.getContextPath() + "/login.html");
                return;
            }
        }
        chain.doFilter(request, response);
    }
}
