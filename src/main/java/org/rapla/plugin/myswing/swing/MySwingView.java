package org.rapla.plugin.myswing.swing;

import java.awt.Color;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.inject.Provider;
import javax.swing.JComponent;

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
import org.rapla.components.calendar.DateRendererAdapter;
import org.rapla.components.calendar.WeekendHighlightRenderer;
import org.rapla.components.calendarview.GroupStartTimesStrategy;
import org.rapla.components.calendarview.swing.AbstractSwingCalendar;
import org.rapla.components.calendarview.swing.SmallDaySlot;
import org.rapla.components.calendarview.swing.SwingMonthView;
import org.rapla.components.calendarview.swing.ViewListener;
import org.rapla.components.iolayer.IOInterface;
import org.rapla.components.util.DateTools;
import org.rapla.entities.domain.AppointmentFormater;
import org.rapla.entities.domain.permission.PermissionController;
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

    public MySwingView(ClientFacade facade, RaplaResources i18n, RaplaLocale raplaLocale, Logger logger, CalendarModel settings, boolean editable, Set<ObjectMenuFactory> objectMenuFactories,
            MenuFactory menuFactory, Provider<DateRenderer> dateRendererProvider, CalendarSelectionModel calendarSelectionModel, RaplaClipboard clipboard,
            ReservationController reservationController, InfoFactory infoFactory, RaplaImages raplaImages, DateRenderer dateRenderer, DialogUiFactory dialogUiFactory, PermissionController permissionController, IOInterface ioInterface, AppointmentFormater appointmentFormater)
                    throws RaplaException
    {
        super(facade, i18n, raplaLocale, logger, settings, editable, objectMenuFactories, menuFactory, dateRendererProvider, calendarSelectionModel, clipboard, reservationController,
                infoFactory, raplaImages, dateRenderer, dialogUiFactory, permissionController, ioInterface, appointmentFormater);
    }

    public static Color DATE_NUMBER_COLOR_HIGHLIGHTED = Color.black;

    protected AbstractSwingCalendar createView(boolean editable) {
        boolean showScrollPane = editable;
        final DateRenderer dateRenderer;
        final DateRendererAdapter dateRendererAdapter; 
        dateRenderer = dateRendererProvider.get();
        dateRendererAdapter = new DateRendererAdapter(dateRenderer, getRaplaLocale().getTimeZone(), getRaplaLocale().getLocale());

        final WeekendHighlightRenderer weekdayRenderer = new WeekendHighlightRenderer();
        /** renderer for weekdays in month-view */
        SwingMonthView monthView = new SwingMonthView( showScrollPane ) {
            
            protected JComponent createSlotHeader(int weekday) {
                JComponent component = super.createSlotHeader( weekday );
                if (isEditable()) {
                    component.setOpaque(true);
                    Color color = weekdayRenderer.getRenderingInfo(weekday, 1, 1, 1).getBackgroundColor();
                    component.setBackground(color);
                }
                return component;
            }

            @Override
            protected SmallDaySlot createSmallslot(int pos, Date date) {
                String header = "" + (pos + 1);
                DateRenderer.RenderingInfo info = dateRendererAdapter.getRenderingInfo(date);
                Color color = getNumberColor( date);
                Color backgroundColor = null;
                String tooltipText = null;
                
                if (info != null) {
                    backgroundColor = info.getBackgroundColor();
                    if (info.getForegroundColor() != null) {
                        color = info.getForegroundColor();
                    }
                    tooltipText = info.getTooltipText();
                    if ( tooltipText != null)
                    {
                         // commons not on client lib path
                        //StringUtils.abbreviate(tooltipText, 15) 
//                      header = tooltipText + " " + (pos+1);
                    }
                }
                final SmallDaySlot smallslot = super.createSmallslot(header, color, backgroundColor);
                if (tooltipText != null) {
                    smallslot.setToolTipText(tooltipText);
                }
                return smallslot;
            }

            protected Color getNumberColor( Date date )
            {
                boolean today = DateTools.isSameDay(getQuery().today().getTime(), date.getTime());
                if ( today)
                {
                    return DATE_NUMBER_COLOR_HIGHLIGHTED;
                }
                else
                {
                    return super.getNumberColor( date );
                }
            }
        };
        monthView.setDaysInView( 25);
        return monthView;
    }

    protected ViewListener createListener() throws RaplaException {
        RaplaCalendarViewListener listener = new RaplaCalendarViewListener(getClientFacade(), getI18n(), getRaplaLocale(), getLogger(), model, view.getComponent(), objectMenuFactories, menuFactory, calendarSelectionModel, clipboard, reservationController, infoFactory, raplaImages, dialogUiFactory, permissionController);
        listener.setKeepTime( true);
        return listener;
    }

    protected RaplaBuilder createBuilder() throws RaplaException
    {
        RaplaBuilder builder = super.createBuilder();
        builder.setSmallBlocks( true );
        GroupStartTimesStrategy strategy = new GroupStartTimesStrategy( );
        builder.setBuildStrategy( strategy );
        return builder;
    }

    protected void configureView() throws RaplaException {
        CalendarOptions calendarOptions = getCalendarOptions();
        Set<Integer> excludeDays = calendarOptions.getExcludeDays();

        view.setExcludeDays( excludeDays );
        view.setToDate(model.getSelectedDate());
    }

    public int getIncrementSize()
    {
        return Calendar.MONTH;
    }


}
