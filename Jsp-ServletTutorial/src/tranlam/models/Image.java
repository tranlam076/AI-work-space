package tranlam.models;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;

public class Image implements HttpSessionBindingListener, HttpSessionActivationListener {
	private String name;
	private int width;
	private int height;
	
	public Image(String name, int width, int height) {
		super();
		this.name = name;
		this.width = width;
		this.height = height;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		event.getSession().getServletContext().log("image in session" + getName());
		System.out.println();		
	}
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		event.getSession().getServletContext().log("image out session" + getName());
		
	}
	@Override
	public void sessionDidActivate(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void sessionWillPassivate(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		
	}
	
	
}
