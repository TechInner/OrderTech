package com.techinner.TechInner.exceptions.handler;

import com.techinner.TechInner.exceptions.administrator.AdministratorNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String details) {

}
