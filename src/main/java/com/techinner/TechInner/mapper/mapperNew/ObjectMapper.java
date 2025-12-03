package com.techinner.TechInner.mapper.mapperNew;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
import java.util.List;

public class ObjectMapper {

    private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    // Converte de DTO para Entity e Entity para DTO
    public static <O, D> D parseObject(O origin, Class<D> destination){
        return mapper.map(origin, destination);
    }

    //Converte litas
    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination){

        List<D> destinationObjects = new ArrayList<D>();
        for (Object o: origin){
            destinationObjects.add(mapper.map(o,destination));
        }
        return destinationObjects;
    }
}
