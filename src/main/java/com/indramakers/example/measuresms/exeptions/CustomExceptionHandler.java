package com.indramakers.example.measuresms.exeptions;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;

import com.indramakers.example.measuresms.model.response.ErrorResponse;

@ControllerAdvice
public class CustomExceptionHandler {

	@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
	@ResponseBody // que la respuesta va a ser personalizada.
	@ExceptionHandler(BusinessException.class)
	public ErrorResponse handleBusinessException(BusinessException exception) {
		return new ErrorResponse(exception.getCode(), exception.getMessage());
	}
	
	 @ResponseStatus(HttpStatus.NOT_FOUND)
	    @ResponseBody
	    @ExceptionHandler(NotFoundException.class)
	    public ErrorResponse handleNotFoundException(NotFoundException exception) {
	        return new ErrorResponse("BAD_PARAMETERS", exception.getMessage());
	    }
	 
	 @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ResponseBody
	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ErrorResponse handleNotFoundException(MethodArgumentNotValidException exception) {
	        return new ErrorResponse("NOT_FOUND", exception.getMessage());
	    }
	 
	 @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	    @ResponseBody
	    @ExceptionHandler(SQLException.class)
	    public ErrorResponse handleException(SQLException exception) {
	        return new ErrorResponse("500", "la locacion tiene uno o mas dispositivos");
	    }

}
