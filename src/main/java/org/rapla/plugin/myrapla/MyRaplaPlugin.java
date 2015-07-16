package org.rapla.plugin.myrapla;

import org.rapla.client.ClientServiceContainer;
import org.rapla.framework.Configuration;
import org.rapla.framework.PluginDescriptor;
import org.rapla.framework.RaplaContextException;

public class MyRaplaPlugin implements PluginDescriptor<ClientServiceContainer>
{
    public static final boolean ENABLE_BY_DEFAULT = true;
      
    /**
     * @throws RaplaContextException 
     * @see org.rapla.framework.PluginDescriptor#provideServices(org.rapla.framework.general.Container)
     */
    public void provideServices(ClientServiceContainer container, Configuration config) throws RaplaContextException {
       if ( !config.getAttributeAsBoolean("enabled", ENABLE_BY_DEFAULT) )
            return;
    }


}

