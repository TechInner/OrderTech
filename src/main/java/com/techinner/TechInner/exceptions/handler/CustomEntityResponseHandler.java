package com.techinner.TechInner.exceptions.handler;

import com.techinner.TechInner.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class CustomEntityResponseHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InternalServerErrorException.class)
    public final ResponseEntity<ExceptionResponse> handlerAllException(InternalServerErrorException ex, WebRequest request){

        ExceptionResponse response = new ExceptionResponse(

                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);

    }


    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException ex, WebRequest request){

        ExceptionResponse response = new ExceptionResponse(

                new Date(),
                ex.getMessage(),
                request.getDescription(false)

        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ExceptionResponse> handleBadRequestExcepetion(BadRequestException ex, WebRequest request){

        ExceptionResponse response = new ExceptionResponse(

                new Date(),
                ex.getMessage(),
                request.getDescription(false)

        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConflictException.class)
    public final ResponseEntity<ExceptionResponse> handleConflictException(ConflictException ex, WebRequest request){

        ExceptionResponse response = new ExceptionResponse(

                new Date(),
                ex.getMessage(),
                request.getDescription(false)

        );

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);

    }

    @ExceptionHandler(ForbiddenException.class)
    public final ResponseEntity<ExceptionResponse> handleForbiddenException(ForbiddenException ex, WebRequest request){

        ExceptionResponse response = new ExceptionResponse(

                new Date(),
                ex.getMessage(),
                request.getDescription(false)

        );

        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public final ResponseEntity<ExceptionResponse> handleUnauthorizedException(UnauthorizedException ex, WebRequest request){

        ExceptionResponse response = new ExceptionResponse(

                new Date(),
                ex.getMessage(),
                request.getDescription(false)

        );

        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

}
