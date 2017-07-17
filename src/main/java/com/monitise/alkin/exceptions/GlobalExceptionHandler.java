package com.monitise.alkin.exceptions;

import com.monitise.alkin.model.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    BaseResponse handleBadRequest(HttpServletRequest req, Exception ex) {
        return new BaseResponse(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ForbiddenAccessException.class)
    @ResponseBody
    BaseResponse handleForbiddenRequest(HttpServletRequest req, Exception ex) {
        return new BaseResponse(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PageNotFoundException.class)
    @ResponseBody
    BaseResponse handlePageNotFoundRequest(HttpServletRequest req, Exception ex) {
        return new BaseResponse(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedAccessException.class)
    @ResponseBody
    BaseResponse handleUnauthorizedAccessRequest(HttpServletRequest req, Exception ex) {
        return new BaseResponse(ex.getMessage());
    }

}
