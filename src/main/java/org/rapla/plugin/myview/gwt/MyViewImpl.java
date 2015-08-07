package org.rapla.plugin.myview.gwt;

import org.rapla.client.base.AbstractView;
import org.rapla.plugin.myview.MyView;
import org.rapla.plugin.myview.MyView.Presenter;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class MyViewImpl extends AbstractView<Presenter> implements MyView<Widget>
{
    
    private final FlowPanel content = new FlowPanel();
    
    public MyViewImpl()
    {
        content.add(new HTML("MyView Content"));
    }

    @Override
    public Widget provideContent()
    {
        return content;
    }

}
