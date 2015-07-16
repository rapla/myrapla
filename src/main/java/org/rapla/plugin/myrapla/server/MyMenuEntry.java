package org.rapla.plugin.myrapla.server;

import org.rapla.framework.RaplaContext;
import org.rapla.servletpages.DefaultHTMLMenuEntry;

public class MyMenuEntry extends DefaultHTMLMenuEntry 
{
	public MyMenuEntry(RaplaContext context) {
		super(context);
	}
		
	@Override
	public String getName() {
		return "MyDist";
	}
	@Override
	public String getLinkName() {
		return "http://rapla.org";
	}
		
}
