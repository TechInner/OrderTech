package com.techinner.TechInner.exceptions.administrator;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AdministratorNotFound extends RuntimeException {

    public AdministratorNotFound(String message){ super(message);}
}
