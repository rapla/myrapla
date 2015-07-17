package org.rapla.gwt.client.myplugin;

import org.rapla.gwt.client.Greeter;

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

