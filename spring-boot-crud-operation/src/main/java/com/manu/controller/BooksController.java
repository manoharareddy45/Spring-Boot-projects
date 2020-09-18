package com.manu.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.manu.model.Books;
import com.manu.service.BooksService;


@RestController
public class BooksController {

	@Autowired
	BooksService booksService;

	@GetMapping("/book")
	private List<Books> getAllBooks() {
		return booksService.getAllBooks();
	}

	@GetMapping("/book/{bookid}")
	private Books getBooks(@PathVariable("bookid") int bookid) {
		return booksService.getBooksById(bookid);
	}

	@DeleteMapping("/book/{bookid}")
	private void deleteBook(@PathVariable("bookid") int bookid) {
		booksService.delete(bookid);
	}

	@PostMapping("/books")
	private int saveBook(@RequestBody Books books) {
		booksService.saveOrUpdate(books);
		return books.getBookid();
	}

	@PutMapping("/books")
	private Books update(@RequestBody Books books) {
		booksService.saveOrUpdate(books);
		return books;
	}
	
	@GetMapping("criteria")
	public List<Books> findByCriteria(String author, String bookname) {
		return booksService.findByCriteria(author, bookname);
	}
}
