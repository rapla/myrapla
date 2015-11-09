package org.rapla.plugin.myrapla.server;

import org.rapla.RaplaResources;
import org.rapla.inject.DefaultImplementation;
import org.rapla.inject.Extension;
import org.rapla.inject.InjectionContext;
import org.rapla.server.extensionpoints.HtmlMainMenu;
import org.rapla.server.servletpages.DefaultHTMLMenuEntry;

import javax.inject.Inject;

@Extension(provides= HtmlMainMenu.class,id="org.rapla.myrapla")
public class MyMenuEntry extends DefaultHTMLMenuEntry implements HtmlMainMenu
{
	@Inject
	public MyMenuEntry(RaplaResources i18n)
	{
		super( i18n.getString("start_rapla_with_applet") + "MyRapla", "rapla?page=raplaapplet");
	}
}
