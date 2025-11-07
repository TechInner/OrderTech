package com.techinner.TechInner.Methods;

import com.techinner.TechInner.exceptions.BadRequestException;

public class Methods
{

    public static Integer ConvertToInt(String id){

        try {
            Integer idParse = Integer.parseInt(id);
            return idParse;
        } catch (NumberFormatException e) {
            throw new BadRequestException("ID must be a number");
        }
    }

    public static void Isnumber(String id){
        if (id == null || id.trim().isEmpty())
            throw new BadRequestException("Id parameter is missing or empty");
    }
}
