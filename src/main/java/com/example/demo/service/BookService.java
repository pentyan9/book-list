package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.form.BookForm;
import com.example.demo.form.EditBookForm;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;

//serviceクラスはcontrollerとrepositoryの橋渡し役的存在！

@Service
@Transactional
public class BookService {
	@Autowired
	BookRepository repository;
	
	/**
	 * データベースから本の一覧を取得する
	 *@return
	 *
	 */	
	public List<Book> findAll(){
		return repository.findAll();
	}
	
	/**
	 * データベースにデータを登録する
	 *@return
	 *
	 */	
	public void insert(BookForm bookForm){
		//データベースに登録する値を保持するインスタンス
		Book book = new Book();
		
		//画面から受け取った値をデータベースに保存するインスタンスに渡す。
		book.setTitle(bookForm.getTitle());
		book.setPrice(bookForm.getPrice());
		
		repository.save(book);
		
	}
	
	//受けっとidからデータを習得して、Formを返却する
	public EditBookForm getOneBook(Integer id) {
		//idを指定して本の情報を収集する
		Book book = repository.findById(id).orElseThrow();
		
		//画面返却用のFormに値を設定する
		com.example.demo.form.EditBookForm editBook = new EditBookForm();
		editBook.setId(book.getId());
		editBook.setTitle(book.getTitle());
		editBook.setPrice(book.getPrice());
		
		return editBook;
	}
	
	// 本を更新する
	public void update(EditBookForm editBook) {
			
	    // データベースに登録する値を保持するインスタンスの作成
	    Book book = new Book();
			
	    // 画面から受け取った値を設定する
	    book.setId(editBook.getId());
	    book.setTitle(editBook.getTitle());
	    book.setPrice(editBook.getPrice());
			
	    // データベースを更新する
	    repository.save(book);
	}
	
	public void delete(Integer id) {
		//idを指定して、データベースからデータを削除する
		repository.deleteById(id);
	}
	

}