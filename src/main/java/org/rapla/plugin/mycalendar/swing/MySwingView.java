package org.rapla.plugin.mycalendar.swing;

import java.awt.Color;
import java.awt.Font;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.inject.Provider;
import javax.swing.JComponent;
import javax.swing.JLabel;

import org.rapla.RaplaResources;
import org.rapla.client.ReservationController;
import org.rapla.client.extensionpoints.ObjectMenuFactory;
import org.rapla.client.internal.RaplaClipboard;
import org.rapla.client.swing.InfoFactory;
import org.rapla.client.swing.MenuFactory;
import org.rapla.client.swing.SwingCalendarView;
import org.rapla.client.swing.images.RaplaImages;
import org.rapla.client.swing.toolkit.DialogUI.DialogUiFactory;
import org.rapla.components.calendar.DateRenderer;
import org.rapla.components.calendar.DateRenderer.RenderingInfo;
import org.rapla.components.calendar.DateRendererAdapter;
import org.rapla.components.calendarview.Builder;
import org.rapla.components.calendarview.GroupStartTimesStrategy;
import org.rapla.components.calendarview.swing.AbstractSwingCalendar;
import org.rapla.components.calendarview.swing.SwingWeekView;
import org.rapla.components.calendarview.swing.ViewListener;
import org.rapla.components.iolayer.IOInterface;
import org.rapla.components.util.DateTools;
import org.rapla.components.util.DateTools.IncrementSize;
import org.rapla.entities.domain.AppointmentFormater;
import org.rapla.facade.CalendarModel;
import org.rapla.facade.CalendarOptions;
import org.rapla.facade.CalendarSelectionModel;
import org.rapla.facade.ClientFacade;
import org.rapla.framework.RaplaException;
import org.rapla.framework.RaplaLocale;
import org.rapla.framework.logger.Logger;
import org.rapla.plugin.abstractcalendar.RaplaBuilder;
import org.rapla.plugin.abstractcalendar.RaplaCalendarViewListener;
import org.rapla.plugin.weekview.client.swing.SwingWeekCalendar;

public class MySwingView extends SwingWeekCalendar implements SwingCalendarView
{

    public MySwingView(ClientFacade facade, RaplaResources i18n, RaplaLocale raplaLocale, Logger logger, CalendarModel settings, boolean editable,
            boolean printing, Set<ObjectMenuFactory> objectMenuFactories, MenuFactory menuFactory, Provider<DateRenderer> dateRendererProvider,
            CalendarSelectionModel calendarSelectionModel, RaplaClipboard clipboard, ReservationController reservationController, InfoFactory infoFactory,
            RaplaImages raplaImages, DateRenderer dateRenderer, DialogUiFactory dialogUiFactory,
            IOInterface ioInterface, AppointmentFormater appointmentFormater) throws RaplaException
    {
        super(facade, i18n, raplaLocale, logger, settings, editable, printing, objectMenuFactories, menuFactory, dateRendererProvider, calendarSelectionModel,
                clipboard, reservationController, infoFactory, raplaImages, dateRenderer, dialogUiFactory, ioInterface,
                appointmentFormater);
    }

    public static Color DATE_NUMBER_COLOR_HIGHLIGHTED = Color.black;

    protected AbstractSwingCalendar createView(boolean showScrollPane)
    {
        final DateRendererAdapter dateRendererAdapter;
        DateRenderer dateRenderer = dateRendererProvider.get();
        dateRendererAdapter = new DateRendererAdapter(dateRenderer, getRaplaLocale().getTimeZone(), getRaplaLocale().getLocale());

        final SwingWeekView wv = new SwingWeekView(showScrollPane)
        {

            protected JComponent createSlotHeader(Integer column)
            {
                JLabel component = (JLabel) super.createSlotHeader(column);
                Date date = getDateFromColumn(column);
                boolean today = DateTools.isSameDay(getQuery().today().getTime(), date.getTime());
                if (today)
                {
                    component.setFont(component.getFont().deriveFont(Font.BOLD));
                }
                if (isEditable())
                {
                    component.setOpaque(true);
                    RenderingInfo info = dateRendererAdapter.getRenderingInfo(date);
                    if (info.getBackgroundColor() != null)
                    {
                        component.setBackground(info.getBackgroundColor());
                    }
                    if (info.getForegroundColor() != null)
                    {
                        component.setForeground(info.getForegroundColor());
                    }
                    if (info.getTooltipText() != null)
                    {
                        component.setToolTipText(info.getTooltipText());
                    }
                }
                return component;
            }

            @Override
            public void rebuild(Builder b)
            {
                // update week
                weekTitle.setText(getI18n().calendarweek(getStartDate()));
                super.rebuild(b);
            }
        };
        return wv;
    }

    protected ViewListener createListener() throws RaplaException
    {
        RaplaCalendarViewListener listener = new RaplaCalendarViewListener(getClientFacade(), getI18n(), getRaplaLocale(), getLogger(), model,
                view.getComponent(), objectMenuFactories, menuFactory, calendarSelectionModel, clipboard, reservationController, infoFactory, raplaImages,
                dialogUiFactory);
        listener.setKeepTime(true);
        return listener;
    }

    protected RaplaBuilder createBuilder() throws RaplaException
    {
        RaplaBuilder builder = super.createBuilder();
        builder.setSmallBlocks(true);
        GroupStartTimesStrategy strategy = new GroupStartTimesStrategy();
        builder.setBuildStrategy(strategy);
        return builder;
    }

    protected void configureView() throws RaplaException
    {
        CalendarOptions calendarOptions = getCalendarOptions();
        Set<Integer> excludeDays = calendarOptions.getExcludeDays();

        view.setExcludeDays(excludeDays);
        view.setToDate(model.getSelectedDate());
    }

    public DateTools.IncrementSize getIncrementSize()
    {
        return IncrementSize.DAY_OF_YEAR;
    }

}
