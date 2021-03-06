package com.xyl.springboot.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xyl.springboot.dto.ResultBean;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e.getMessage());
        mav.addObject("url", req.getRequestURL());
        System.out.println("url:"+req.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }
	
    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public ResultBean<String> jsonErrorHandler(HttpServletRequest req, MyException e) throws Exception {
    	ResultBean<String> r = new ResultBean<>();
        r.setMessage(e.getMessage());
        r.setCode(ResultBean.ERROR);
        r.setData("Fail");
        r.setUrl(req.getRequestURL().toString());
        return r;
    }
    
}
