package com.umldesigner.infrastructure.mapper;

import java.util.List;
import java.util.Set;

public interface GeneralMapper<Dto, Entity> {

  public Dto entityToDto(Entity entity);

  public Entity dtoToEntity(Dto dto);

  public <S, T> List<T> mapList(List<S> source, Class<T> targetClass);

  public <S, T> Set<T> mapSet(Set<S> source, Class<T> targetClass);
}
