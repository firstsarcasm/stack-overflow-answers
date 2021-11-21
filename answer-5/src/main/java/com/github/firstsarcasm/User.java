package com.github.firstsarcasm;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class User {

	public Integer id;
	public String name;

	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
	public Date date;

	public User(String name, Date date) {
		this.name = name;
		this.date = date;
	}

}
