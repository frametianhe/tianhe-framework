package com.tianhe.framework.designpatterns.study.composite;

import java.util.List;

public class File implements IFile{

	private String name;
	
	public File(String name) {
		this.name = name;
	}

	@Override
	public void display() {
		System.out.println(name);
	}

	@Override
	public boolean add(IFile iFile) {
		return false;
	}

	@Override
	public boolean remove(IFile iFile) {
		return false;
	}

	@Override
	public List<IFile> getChild() {
		return null;
	}

}
