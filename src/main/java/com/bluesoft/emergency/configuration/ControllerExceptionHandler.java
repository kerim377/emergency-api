package com.bluesoft.emergency.configuration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ControllerExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

  @ResponseStatus(NOT_FOUND)
  @ResponseBody
  @ExceptionHandler(NotFoundException.class)
  public ApiError notFoundExceptionHandler(NotFoundException ex, HttpServletRequest request) {
    logger.warn("NOT_FOUND: URL:{} Exception: {}", getRequestUri(request), ex.getMessage(), ex);
    return ApiError.builder()
        .exceptionCode(NOT_FOUND.value())
        .exceptionMessage(NOT_FOUND.getReasonPhrase())
        .descriptionMessage(ex.getMessage())
        .build();
  }

  @ResponseStatus(BAD_REQUEST)
  @ResponseBody
  @ExceptionHandler({BadRequestException.class, MethodArgumentNotValidException.class})
  public ApiError badRequestExceptionHandler(Exception ex, HttpServletRequest request) {
    logger.warn("BAD_REQUEST: URL:{} Exception: {}", getRequestUri(request), ex.getMessage(), ex);
    return ApiError.builder()
        .exceptionCode(BAD_REQUEST.value())
        .exceptionMessage(BAD_REQUEST.getReasonPhrase())
        .descriptionMessage(getDescriptionMessage(ex))
        .build();
  }

  private String getDescriptionMessage(Exception ex) {
    try {
      MethodArgumentNotValidException exception = (MethodArgumentNotValidException) ex;
      List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();
      return allErrors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(";"));
    }
    catch (Exception e) {
      logger.warn("Exception is not instance of of MethodArgumentNotValidException", ex);
      return ex.getMessage();
    }

  }

  @ExceptionHandler(value = {RuntimeException.class})
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ApiError internalServerExceptionHandler(RuntimeException ex, HttpServletRequest request) {
    logger.error("INTERNAL_SERVER_ERROR: URL:{} Exception: {}", getRequestUri(request), ex.getMessage(), ex);
    return ApiError.builder()
        .exceptionCode(INTERNAL_SERVER_ERROR.value())
        .exceptionMessage(INTERNAL_SERVER_ERROR.getReasonPhrase())
        .build();
  }

  private String getRequestUri(HttpServletRequest request) {
    return request == null ? null : request.getRequestURI();
  }
}
