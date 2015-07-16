/*--------------------------------------------------------------------------*
 | Copyright (C) 2006 Christopher Kohlhaas                                  |
 |                                                                          |
 | This program is free software; you can redistribute it and/or modify     |
 | it under the terms of the GNU General Public License as published by the |
 | Free Software Foundation. A copy of the license has been included with   |
 | these distribution in the COPYING file, if not go to www.fsf.org         |
 |                                                                          |
 | As a special exception, you are granted the permissions to link this     |
 | program with every library, which license fulfills the Open Source       |
 | Definition as published by the Open Source Initiative (OSI).             |
 *--------------------------------------------------------------------------*/
package org.rapla.plugin.myrapla.server;
import org.rapla.framework.Configuration;
import org.rapla.framework.PluginDescriptor;
import org.rapla.framework.RaplaContextException;
import org.rapla.framework.logger.Logger;
import org.rapla.plugin.myrapla.MyRaplaPlugin;
import org.rapla.server.RaplaServerExtensionPoints;
import org.rapla.server.ServerServiceContainer;

/**
   This is a demonstration of a rapla-plugin. It adds a sample usecase and option
   to the rapla-system.
 */

public class MyRaplaServerPlugin implements PluginDescriptor<ServerServiceContainer>
{
    Logger logger;
    
    public MyRaplaServerPlugin(Logger logger) {
        this.logger = logger;
    }
   
    public Logger getLogger() {
        return logger;
    }

    /**
     * @throws RaplaContextException 
     * @see org.rapla.framework.PluginDescriptor
     */
    public void provideServices(ServerServiceContainer container, Configuration config) throws RaplaContextException {
        if ( !config.getAttributeAsBoolean("enabled", MyRaplaPlugin.ENABLE_BY_DEFAULT) )
        	return;
        container.addContainerProvidedComponent( RaplaServerExtensionPoints.HTML_MAIN_MENU_EXTENSION_POINT, MyMenuEntry.class);
        //container.addRemoteJsonFactory( RaplaJsonService.class, RaplaJsonServiceFactory.class, config);
    }

}

