package com.uco.TrafficAgent.infrastructure.filter;

import com.uco.TrafficAgent.infrastructure.service.token.ServiceValidateToken;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FilterAuth extends OncePerRequestFilter {

    private static final String STRING_EMPTY = "";
    private static final String WILDCARD_ALL = "*";
    private static final String REGEX_ALL = "\\*";
    private static final String MESSAGE_TOKEN_INVALID = "Token no existe, invalido or vencido.";

    private final ServiceValidateToken serviceValidateToken;
    private final String[] excludePaths;

    public FilterAuth(ServiceValidateToken serviceValidateToken, String[] excludePaths) {
        this.serviceValidateToken = serviceValidateToken;
        this.excludePaths = excludePaths;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, IOException, ServletException {

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(!this.serviceValidateToken.isValid(token)) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(),MESSAGE_TOKEN_INVALID);
            return;
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();

        boolean shouldNotFilter = false;

        for (int i = 0; i < excludePaths.length; i++) {

            String excludePath = excludePaths[i];

            if(excludePath.endsWith(WILDCARD_ALL)) {
                if(path.startsWith(excludePath.replaceAll(REGEX_ALL, STRING_EMPTY))) {
                    shouldNotFilter = true;
                }
            }else if(excludePath.startsWith(WILDCARD_ALL)) {
                if(path.endsWith(excludePath.replaceAll(REGEX_ALL, STRING_EMPTY))) {
                    shouldNotFilter = true;
                }
            }
            else {
                if(path.equals(excludePath)) {
                    shouldNotFilter = true;
                }
            }
        }

        return shouldNotFilter;
    }
}
