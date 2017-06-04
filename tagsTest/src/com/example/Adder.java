package com.example;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class Adder extends SimpleTagSupport {
	private int x;
	private int y;

	public void doTag() throws JspException, IOException {
		getJspContext().getOut().write(x+"+"+y+"="+(x+y));
	}
	
	public void setX(int x){
		this.x=x;
	}
	
	public void setY(int y){
		this.y=y;
	}
	
	
}