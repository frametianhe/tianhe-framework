package com.tianhe.framework.designpatterns.study.iterator;

import java.util.Iterator;

/**
 * 迭代模式
 * @author:姜伟锋
 * @DATE:2016年3月19日 @TIME: 下午9:04:27
 */
public class Main {

	public static void main(String[] args) {
		BookList bookList = new BookList();

		Book book = new Book("001", "java编程思想",90.0);
		Book book2 = new Book("002", "java并发编程的艺术",60.0);

		bookList.addBook(book);
		bookList.addBook(book2);

//		while(bookList.hasNext()){
//			Book book3 = bookList.getNext();
//			book3.display();
//		}

//		List<Book> bookDataList = bookList.getBookList();
//		for (int i = 0; i < bookDataList.size(); i++) {
//			Book book3 = bookDataList.get(i);
//			book3.display();
//		}

		Iterator it = bookList.iterator();
		for(;it.hasNext();){
			Book book3 = (Book) it.next();
			book3.display();
		}

	}
}
