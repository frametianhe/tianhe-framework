package com.tianhe.framework.netty.online.client;//package com.tianhe.netty.lianxi.use.channel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author:weifeng.jiang
 * @DATE:2017/5/11 @TIME:20:58
 */
public class DemoServlet extends HttpServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.err.println("content="+req.getParameter("content"));
        try {
            PrintWriter writer = resp.getWriter();
            writer.write("nihao");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
