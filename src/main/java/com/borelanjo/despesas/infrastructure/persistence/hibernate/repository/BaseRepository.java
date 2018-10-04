package com.borelanjo.despesas.infrastructure.persistence.hibernate.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface BaseRepository<T,ID> extends Repository<T, ID>{
  
  List<T> findAll();
  
  T save(T entity);
  
  Optional<T> findById(ID id);

  void deleteAll();

}
