package org.rapla.plugin.myview;

import javax.inject.Inject;

import org.rapla.client.base.CalendarPlugin;
import org.rapla.framework.RaplaException;
import org.rapla.plugin.myview.MyView.Presenter;

public class MyViewPresenter<W> implements Presenter, CalendarPlugin<W>
{
    private final MyView<W> view;

    @SuppressWarnings("unchecked")
    @Inject
    public MyViewPresenter(@SuppressWarnings("rawtypes") MyView view)
    {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public String getName()
    {
        return "MyView";
    }

    @Override
    public W provideContent()
    {
        return this.view.provideContent();
    }

    @Override
    public void updateContent() throws RaplaException
    {
        // Update view
    }

}
