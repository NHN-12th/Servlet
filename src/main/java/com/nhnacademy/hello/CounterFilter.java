package com.nhnacademy.hello;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class CounterFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        CounterUtils.increaseCounter(request.getServletContext());
        chain.doFilter(request, response);
        log.error("counter:{}", request.getServletContext().getAttribute("counter"));
    }
}
