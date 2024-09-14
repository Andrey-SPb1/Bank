package com.bank.mapper;

public interface Mapper<mapFrom, mapTo> {

    mapTo mapDto(mapFrom obj);

    mapFrom map(mapTo obj);
}
