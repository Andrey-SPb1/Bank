package com.bank.mapper;

import java.util.concurrent.ExecutionException;

public interface Mapper<mapFrom, mapTo> {

    mapTo mapToDto(mapFrom obj);

    mapFrom mapToEntity(mapTo obj) throws ExecutionException, InterruptedException;
}
