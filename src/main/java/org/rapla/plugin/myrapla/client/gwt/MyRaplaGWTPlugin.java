package org.rapla.plugin.myrapla.client.gwt;

import org.rapla.client.gwt.Greeter;

import com.google.gwt.inject.client.GinModule;
import com.google.gwt.inject.client.binder.GinBinder;
import com.google.gwt.inject.client.multibindings.GinMultibinder;

public class MyRaplaGWTPlugin implements GinModule{
    @Override
    public void configure(GinBinder binder) {
        GinMultibinder<Greeter> uriBinder = GinMultibinder.newSetBinder(binder, Greeter.class);
        uriBinder.addBinding().to(MyRaplaGreeter.class);
    }
    
}

