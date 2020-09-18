package com.manu.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.manu.model.Books;

public interface BooksRepository extends CrudRepository<Books, Integer>, JpaSpecificationExecutor<Books> {
}
