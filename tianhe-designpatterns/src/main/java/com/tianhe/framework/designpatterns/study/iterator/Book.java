package com.tianhe.framework.designpatterns.study.iterator;

public class Book {

	private String isbn;
	private String name;
	private double price;

	public Book(String isbn, String name, double price) {
		super();
		this.isbn = isbn;
		this.name = name;
		this.price = price;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void display() {
		System.out.println("Book [isbn=" + isbn + ", name=" + name + ", price=" + price
				+ "]");
	}
	
	

}
