package org.rapla.plugin.myview.client;

import java.util.Date;

import javax.inject.Inject;

import org.rapla.client.base.CalendarPlugin;
import org.rapla.components.util.DateTools;
import org.rapla.framework.RaplaException;
import org.rapla.inject.Extension;

@Extension(provides = CalendarPlugin.class, id = "myview")
public class MyViewPresenter implements MyView.Presenter, CalendarPlugin
{
    private final MyView view;

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
    public Object provideContent()
    {
        return this.view.provideContent();
    }

    @Override
    public void updateContent() throws RaplaException
    {
        // Update view
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }

    @Override
    public Date calcNext(Date currentDate)
    {
        return DateTools.addDays(currentDate, 7);
    }

    @Override
    public Date calcPrevious(Date currentDate)
    {
        return DateTools.addDays(currentDate, -7);
    }

}
