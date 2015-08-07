package org.rapla.plugin.myview;

import org.rapla.client.base.View;
import org.rapla.plugin.myview.MyView.Presenter;

public interface MyView<W> extends View<Presenter>
{
    public interface Presenter
    {
    }

    W provideContent();
}
