package com.kxy.bkd.handler;

import org.junit.platform.commons.util.AnnotationUtils;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.AnnotatedArrayType;

//拦截器
@ControllerAdvice
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHander(HttpServletRequest request,Exception e) throws Exception {
        logger.error("Requst URL : {}, Exception : {}" ,request.getRequestURI());

        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){

            throw e;

        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("URL",request.getRequestURI());
        mv.addObject("exception",e);
        mv.setViewName("error/error");
        return mv;




    }
}
