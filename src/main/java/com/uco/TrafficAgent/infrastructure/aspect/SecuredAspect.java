package com.uco.TrafficAgent.infrastructure.aspect;

import com.uco.TrafficAgent.infrastructure.aspect.exception.ExceptionUserUnauthorized;
import com.uco.TrafficAgent.infrastructure.aspect.service.AuthorizationService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class SecuredAspect {
    private static final String MESSAGE_USER_UNATHORIZED = "Forbidden!";

    private final AuthorizationService authorizationService;

    public SecuredAspect(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @Before("@annotation(Secured)")
    public void processMethodsAnnotatedWithProjectSecuredAnnotation(JoinPoint joinPoint) throws Throwable {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Secured annotation = method.getAnnotation(Secured.class);

        List<String> rolesToAuthorized = Arrays.asList(annotation.roles());

        boolean isUserAuthorized = this.authorizationService.isAuthorized(rolesToAuthorized);

        analyzeIfCanContinue(isUserAuthorized);
    }

    private void analyzeIfCanContinue(boolean isUserAuthorized) {
        if (!isUserAuthorized) {
            throw new ExceptionUserUnauthorized(MESSAGE_USER_UNATHORIZED);
        }
    }
}
