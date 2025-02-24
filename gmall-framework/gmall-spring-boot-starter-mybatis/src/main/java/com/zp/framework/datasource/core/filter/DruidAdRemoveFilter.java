package com.zp.framework.datasource.core.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Author : zhengpanone
 * Date : 2024/7/7 21:28
 * Version : v1.0.0
 * Description: Druid 底部广告过滤器
 */
public class DruidAdRemoveFilter extends OncePerRequestFilter {

    /**
     * common.js 的路径
     */
    private static final String COMMON_JS_ILE_PATH = "support/http/resources/js/common.js";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 创建一个包装响应的类
        CharResponseWrapper responseWrapper = new CharResponseWrapper(response);

        // 继续执行过滤器链
        filterChain.doFilter(request, response);

        // 获取原始响应内容
        String content = responseWrapper.toString();

        // 重置缓冲区，响应头不会被重置
        response.resetBuffer();

        // 移除底部广告的 HTML 代码（根据实际情况调整）
        content = content.replaceAll("<a.*?banner\\\"></a><br/>","");
        String filteredContent = content.replaceAll("<div.*?powered.*?</div>", "");
        // 将过滤后的内容写回到响应中
        response.getWriter().write(filteredContent);
    }


    // 定义一个响应包装类，用于捕获响应的内容
    private static class CharResponseWrapper extends HttpServletResponseWrapper {
        private final CharArrayWriter charWriter = new CharArrayWriter();
        private final PrintWriter writer;

        public CharResponseWrapper(HttpServletResponse response) {
            super(response);
            this.writer = new PrintWriter(charWriter);
        }

        @Override
        public PrintWriter getWriter() {
            return writer;
        }

        @Override
        public String toString() {
            writer.flush(); // 确保所有内容被写入
            return charWriter.toString();
        }
    }
}
