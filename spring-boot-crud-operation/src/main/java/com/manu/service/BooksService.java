package com.manu.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.manu.model.Books;
import com.manu.repository.BooksRepository;


@Service
public class BooksService {
	
	@Autowired
	BooksRepository booksRepository;
	
	public List<Books> getAllBooks() {
		List<Books> books = new ArrayList<Books>();
		booksRepository.findAll().forEach(books1 -> books.add(books1));
		return books;
	}

	public Books getBooksById(int id) {
		return booksRepository.findById(id).get();
	}

	public void saveOrUpdate(Books books) {
		booksRepository.save(books);
	}

	public void delete(int id) {
		booksRepository.deleteById(id);
	}

	public void update(Books books, int bookid) {
		booksRepository.save(books);
	}
	
	public List<Books> findByCriteria(String author, String bookname){
	       return booksRepository.findAll(new Specification<Books>() {
	           @Override
	           public Predicate toPredicate(Root<Books> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
	               List<Predicate> predicates = new ArrayList<>();
	               if(bookname !=null) {
	                   predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("bookname"), "%"+bookname+"%")));
	               }
	               if(author != null){
	                   predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("author"), author)));
	               }
	               return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
	           }
	       });
	   }
}