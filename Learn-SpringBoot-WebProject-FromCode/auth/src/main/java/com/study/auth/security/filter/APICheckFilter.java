package com.study.auth.security.filter;

import lombok.extern.log4j.Log4j2;
import net.minidev.json.JSONObject;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 스프링 시큐리티의 여러 필터 중에서 맨 마지막 필터로 동작함
 */
@Log4j2
public class APICheckFilter extends OncePerRequestFilter {

    private AntPathMatcher antPathMatcher;
    private String pattern;

    public APICheckFilter(String pattern) {
        this.antPathMatcher = new AntPathMatcher();
        this.pattern = pattern;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

        log.info("REQUEST URI: " + request.getRequestURI());
        log.info(antPathMatcher.match(pattern, request.getRequestURI()));

        if(antPathMatcher.match(pattern, request.getRequestURI())) {

            log.info("APICheckFilter............................................");
            log.info("APICheckFilter............................................");
            log.info("APICheckFilter............................................");

            boolean checkHeader = checkAuthHeader(request);

            if(checkHeader) {
                filterChain.doFilter(request, response);
            } else {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                // json 리턴 및 한글깨짐 수정
                response.setContentType("application/json; charset=utf-8");

                JSONObject jsonObject = new JSONObject();
                String message = "FAIL CHECK API TOKEN";
                jsonObject.put("code", "403");
                jsonObject.put("message", message);

                PrintWriter out = response.getWriter();
                out.print(jsonObject);
            }
            return ;
        }
        filterChain.doFilter(request, response);
    }

    private boolean checkAuthHeader(HttpServletRequest request) {
        boolean checkResult = false;

        String authHeader = request.getHeader("Authorization");

        if(StringUtils.hasText(authHeader)) {
            log.info("Authorization exist: " + authHeader);
            if(authHeader.equals("12345678")) {
                checkResult = true;
            }
        }
        return checkResult;
    }
}
