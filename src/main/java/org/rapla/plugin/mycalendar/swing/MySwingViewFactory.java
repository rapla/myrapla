package org.rapla.plugin.mycalendar.swing;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.rapla.RaplaResources;
import org.rapla.client.ReservationController;
import org.rapla.client.extensionpoints.ObjectMenuFactory;
import org.rapla.client.internal.RaplaClipboard;
import org.rapla.client.swing.InfoFactory;
import org.rapla.client.swing.MenuFactory;
import org.rapla.client.swing.SwingCalendarView;
import org.rapla.client.swing.extensionpoints.SwingViewFactory;
import org.rapla.client.swing.images.RaplaImages;
import org.rapla.client.swing.toolkit.DialogUI.DialogUiFactory;
import org.rapla.components.calendar.DateRenderer;
import org.rapla.components.iolayer.IOInterface;
import org.rapla.entities.domain.AppointmentFormater;
import org.rapla.entities.domain.permission.PermissionController;
import org.rapla.facade.CalendarModel;
import org.rapla.facade.CalendarSelectionModel;
import org.rapla.facade.ClientFacade;
import org.rapla.framework.RaplaException;
import org.rapla.framework.RaplaLocale;
import org.rapla.framework.logger.Logger;
import org.rapla.inject.Extension;
import org.rapla.plugin.tableview.TableViewPlugin;

@Singleton
@Extension(provides = SwingViewFactory.class, id = TableViewPlugin.TABLE_EVENT_VIEW)
public class MySwingViewFactory implements SwingViewFactory
{

    private ImageIcon icon;
    private ClientFacade facade;
    private RaplaResources i18n;
    private RaplaLocale raplaLocale;
    private Logger logger;
    private Set<ObjectMenuFactory> objectMenuFactories;
    private MenuFactory menuFactory;
    private Provider<DateRenderer> dateRendererProvider;
    private CalendarSelectionModel calendarSelectionModel;
    private RaplaClipboard clipboard;
    private ReservationController reservationController;
    private InfoFactory infoFactory;
    private RaplaImages raplaImages;
    private DateRenderer dateRenderer;
    private DialogUiFactory dialogUiFactory;
    private PermissionController permissionController;
    private IOInterface ioInterface;
    private AppointmentFormater appointmentFormater;

    @Inject
    public MySwingViewFactory(ClientFacade facade, RaplaResources i18n, RaplaLocale raplaLocale, Logger logger, Set<ObjectMenuFactory> objectMenuFactories,
            MenuFactory menuFactory, Provider<DateRenderer> dateRendererProvider, CalendarSelectionModel calendarSelectionModel, RaplaClipboard clipboard,
            ReservationController reservationController, InfoFactory infoFactory, RaplaImages raplaImages, DateRenderer dateRenderer,
            DialogUiFactory dialogUiFactory, PermissionController permissionController, IOInterface ioInterface, AppointmentFormater appointmentFormater)
    {
        this.facade = facade;
        this.i18n = i18n;
        this.raplaLocale = raplaLocale;
        this.logger = logger;
        this.objectMenuFactories = objectMenuFactories;
        this.menuFactory = menuFactory;
        this.dateRendererProvider = dateRendererProvider;
        this.calendarSelectionModel = calendarSelectionModel;
        this.clipboard = clipboard;
        this.reservationController = reservationController;
        this.infoFactory = infoFactory;
        this.raplaImages = raplaImages;
        this.dateRenderer = dateRenderer;
        this.dialogUiFactory = dialogUiFactory;
        this.permissionController = permissionController;
        this.ioInterface = ioInterface;
        this.appointmentFormater = appointmentFormater;

    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }

    @Override
    public SwingCalendarView createSwingView(CalendarModel model, boolean editable, boolean printing) throws RaplaException
    {
        return new MySwingView(facade, i18n, raplaLocale, logger, model, editable, printing, objectMenuFactories, menuFactory, dateRendererProvider,
                calendarSelectionModel, clipboard, reservationController, infoFactory, raplaImages, dateRenderer, dialogUiFactory, permissionController,
                ioInterface, appointmentFormater);
    }

    @Override
    public String getViewId()
    {
        return "mySwingView";
    }

    @Override
    public String getMenuSortKey()
    {
        return "B";
    }

    @Override
    public String getName()
    {
        return "exampleSwingView";
    }

    @Override
    public Icon getIcon()
    {
        if (icon == null)
        {
            icon = RaplaImages.getIcon("/org/rapla/plugin/weekview/images/week.png");
        }
        return icon;
    }

}
