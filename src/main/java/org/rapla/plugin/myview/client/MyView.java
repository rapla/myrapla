package org.rapla.plugin.myview.client;

import org.rapla.client.base.View;

public interface MyView<W> extends View<MyView.Presenter>
{
    public interface Presenter
    {
    }

    W provideContent();
}
