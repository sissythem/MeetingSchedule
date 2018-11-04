package gr.demokritos.meetingscheduler.windows;

import com.vaadin.event.ShortcutAction;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import gr.demokritos.meetingscheduler.utils.EnumUtils;
import gr.demokritos.meetingscheduler.utils.GeneralUtils;
import gr.demokritos.meetingscheduler.utils.VaadinElementUtils;

public class Message extends Window {
    private static final ThemeResource ERROR_ICON = new ThemeResource(GeneralUtils.ERROR_ICON_PATH);
    private static final ThemeResource INFO_ICON = new ThemeResource(GeneralUtils.INFO_ICON_PATH);
    private static final ThemeResource WARNING_ICON = new ThemeResource(GeneralUtils.WARN_ICON_PATH);
    private static final ThemeResource SUCCESS_ICON = new ThemeResource(GeneralUtils.SUCCESS_ICON_PATH);

    public Message(String caption, String message, EnumUtils.MessageType messageType){
        setCaption(caption);
        HorizontalLayout iconAndMessage = createMessageLayout(message, messageType);
        VerticalLayout vLayout = createWindowContent(iconAndMessage);
        this.setContent(vLayout);
        this.setResizable(false);
        this.setSizeUndefined();
        this.setModal(true);
    }

    public static Image initImageIcon(EnumUtils.MessageType messageType) {
        Image icon = new Image();
        switch (messageType) {
            case INFO:
                icon.setIcon(INFO_ICON);
                break;
            case WARN:
                icon.setIcon(WARNING_ICON);
                break;
            case ERROR:
                icon.setIcon(ERROR_ICON);
                break;
            case SUCCESS:
                icon.setIcon(SUCCESS_ICON);
        }
        return icon;
    }

    private VerticalLayout createWindowContent(HorizontalLayout iconAndMessage) {
        VerticalLayout vLayout = new VerticalLayout();
        Button okBtn = new Button(VaadinElementUtils.OK_BUTTON);
        okBtn.addClickListener(event -> this.close());
        okBtn.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        vLayout.setMargin(true);
        vLayout.setSpacing(true);
        vLayout.addComponents(iconAndMessage, okBtn);
        vLayout.setComponentAlignment(okBtn, Alignment.MIDDLE_CENTER);
        return vLayout;
    }

    private HorizontalLayout createMessageLayout(String message, EnumUtils.MessageType messageType) {
        HorizontalLayout iconAndMessage = new HorizontalLayout();
        Image icon = initImageIcon(messageType);
        Label messageLbl = new Label(message, ContentMode.HTML);
        iconAndMessage.setSpacing(true);
        iconAndMessage.addComponents(icon, messageLbl);
        iconAndMessage.setComponentAlignment(icon, Alignment.MIDDLE_CENTER);
        iconAndMessage.setComponentAlignment(messageLbl, Alignment.MIDDLE_CENTER);
        iconAndMessage.setExpandRatio(messageLbl, 1);
        return iconAndMessage;
    }

    public static void show(String caption, String message, EnumUtils.MessageType messageType){
        Message md = new Message(caption, message, messageType);
        UI.getCurrent().addWindow(md);
    }

}
