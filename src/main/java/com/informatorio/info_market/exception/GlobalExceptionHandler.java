package com.informatorio.info_market.exception;

import com.informatorio.info_market.dto.error.ErrorResponseDto;
import com.informatorio.info_market.exception.badRequest.StockInsuficienteException;
import com.informatorio.info_market.exception.notFound.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Map<String,String>>> handleValidationException(MethodArgumentNotValidException ex, WebRequest webRequest){
        List<Map<String,String>> errores = ex.getFieldErrors().stream()
                .map(fieldError -> {
                    Map<String,String> error =new HashMap<>();
                    error.put(fieldError.getField(),fieldError.getDefaultMessage());
                    return error;
                }).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFoundException(NotFoundException e, WebRequest webRequest){

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                e,
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(StockInsuficienteException.class)
    public ResponseEntity<ErrorResponseDto>handleStockInsuficienteException(StockInsuficienteException e,WebRequest webRequest){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                e,
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST

        );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }
}
