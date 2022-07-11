package com.umldesigner.infrastructure.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

public abstract class AbstractGeneralMapper {

  public ModelMapper modelMapper;

  public AbstractGeneralMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
    modelMapperConfig();
  }

  public void modelMapperConfig() {
  }

  public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
    return source
        .stream()
        .map(element -> modelMapper.map(element, targetClass))
        .collect(Collectors.toList());
  }

  // I have no idea how I coded this
  public <S, T> Set<T> mapSet(Set<S> source, Class<T> targetClass) {
    return source
        .stream()
        .map(element -> modelMapper.map(element, targetClass))
        .collect(Collectors.toSet());
  }
}
