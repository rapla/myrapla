package org.rapla.plugin.myview.gwt;

import javax.inject.Inject;

import org.rapla.client.base.AbstractView;
import org.rapla.inject.DefaultImplementation;
import org.rapla.inject.InjectionContext;
import org.rapla.plugin.myview.MyView;
import org.rapla.plugin.myview.MyView.Presenter;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;

@DefaultImplementation(of=MyView.class, context=InjectionContext.gwt)
public class MyViewImpl extends AbstractView<Presenter> implements MyView<IsWidget>
{
    
    private final FlowPanel content = new FlowPanel();
    
    @Inject
    public MyViewImpl()
    {
        content.add(new HTML("MyView Content"));
    }

    @Override
    public IsWidget provideContent()
    {
        return content;
    }

}
