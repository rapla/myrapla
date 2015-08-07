package org.rapla.plugin.myview.gwt;

import org.rapla.client.base.CalendarPlugin;
import org.rapla.plugin.myview.MyView;
import org.rapla.plugin.myview.MyViewPresenter;

import com.google.gwt.inject.client.GinModule;
import com.google.gwt.inject.client.binder.GinBinder;
import com.google.gwt.inject.client.multibindings.GinMultibinder;

public class MyViewPlugin implements GinModule
{
    @Override
    public void configure(GinBinder binder)
    {
        GinMultibinder<CalendarPlugin> uriBinder = GinMultibinder.newSetBinder(binder, CalendarPlugin.class);
        uriBinder.addBinding().to(MyViewPresenter.class);
        binder.bind(MyView.class).to(MyViewImpl.class);
    }
}
