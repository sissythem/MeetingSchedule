package gr.demokritos.meetingscheduler.utils;

import com.vaadin.server.VaadinSession;
import gr.demokritos.meetingscheduler.business.dto.UserDto;

public class GeneralUtils {

    public static String SESSION_USER 					= "user";

    /** Logo and sublogo **/
    public static String APP_LOGO						= "Meeting Scheduler";
    public static String APP_SUB_LOGO					= "Easy way to handle meetings";

    /** Login page**/
    public static String SIGN_IN 						= "Sign in";
    public static String USERNAME 						= "Username";
    public static String PASSWORD 						= "Password";

    /** Menu bars **/
    public static String LOGOUT							= "Sign out";
    public static String MEMBERS						= "Members";
    public static String USERS							= "Users";
    public static String AVAILABILITIES                 = "Availabilities";
    public static String MEETINGS                       = "Meetings";
    public static String PROFILE						= "Profile";
    public static String HELP							= "Help";

    /** Images & Icon paths **/
    public static String LOGO_ICON_PATH					= "images/calendar.png";
    public static String ERROR_ICON_PATH				= "icons/error_icon.svg";
    public static String INFO_ICON_PATH					= "icons/information_icon.svg";
    public static String WARN_ICON_PATH					= "icons/warning_icon.svg";
    public static String SUCCESS_ICON_PATH				= "icons/confirmation_icon.svg";
    public static String CLOSE_ICON_PATH				= "icons/close_exit_icon.svg";

    public static UserDto getUserFromSession() {
        return (UserDto) VaadinSession.getCurrent().getAttribute(SESSION_USER);
    }

}
