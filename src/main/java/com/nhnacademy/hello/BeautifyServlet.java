package com.nhnacademy.hello;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class BeautifyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        String html = req.getParameter("html");
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");

        try (PrintWriter writer = resp.getWriter()) {
            writer.println(Jsoup.parse(html));
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }
}
