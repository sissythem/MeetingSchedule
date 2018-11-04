package gr.demokritos.meetingscheduler.windows;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import gr.demokritos.meetingscheduler.MeetingUI;
import gr.demokritos.meetingscheduler.business.dto.MemberDto;
import gr.demokritos.meetingscheduler.grids.MembersGrid;
import gr.demokritos.meetingscheduler.utils.EnumUtils;
import gr.demokritos.meetingscheduler.utils.GeneralUtils;
import gr.demokritos.meetingscheduler.utils.MessagesUtils;
import gr.demokritos.meetingscheduler.utils.VaadinElementUtils;
import org.apache.commons.lang3.StringUtils;

public class MemberWindow extends ParentWindow {

    private EnumUtils.Action action;
    private MembersGrid membersGrid;
    private TextField nameTf = new TextField("");
    private TextField lastNameTf = new TextField("");
    private TextField emailTf = new TextField("");
    private final Binder<MemberDto> binder = new Binder<>(MemberDto.class);

    private static final ThemeResource WARNING_ICON = new ThemeResource(GeneralUtils.WARN_ICON_PATH);

    public MemberWindow(EnumUtils.Action action, String title, MembersGrid membersGrid) {
        super();
        this.action = action;
        this.membersGrid = membersGrid;
        this.setCaption(title);
        setUpFormLayout();
        setUpWindow();
    }

    @Override
    public void onSave() {
        switch (action) {
            case ADD:
                addNewMember();
                break;
            case EDIT:
                updateMember();
                break;
            case REMOVE:
                deleteMember();
                break;
            case VIEW:
                this.close();
                break;
        }
        this.close();
    }

    private void setUpFormLayout() {
        switch (action) {
            case ADD:
                onAddLayout();
                break;
            case EDIT:
                onEditLayout();
                break;
            case REMOVE:
                onRemoveLayout();
                break;
            case VIEW:
                onViewLayout();
                break;
        }
    }

    private void onAddLayout() {
        createFormLayout();
        removeRequiredIndicators();
    }

    private void onEditLayout() {
        createFormLayout();
        fillForm(membersGrid.getSelectedMember());
    }

    private void onViewLayout() {
        createFormLayout();
        fillForm(membersGrid.getSelectedMember());
        setReadOnly();
    }

    private void onRemoveLayout() {
        this.formLayout = new VerticalLayout();
        HorizontalLayout hl = new HorizontalLayout();
        getSaveBtn().setCaption(VaadinElementUtils.DELETE_BUTTON);
        Image icon = new Image();
        icon.setIcon(WARNING_ICON);
        Label label = new Label(MessagesUtils.DELETE_MEMBER_WARNING + " " + membersGrid.getSelectedMember().getName() + MessagesUtils.QUESTION_MARK);
        hl.addComponents(icon, label);
        formLayout.addComponent(hl);
    }

    private void createFormLayout() {
        formLayout = new VerticalLayout();
        setUpTextFields();
        addValidation();
        formLayout.addComponents(nameTf, lastNameTf, emailTf);
    }

    private void setUpTextFields() {
        nameTf.setPlaceholder(VaadinElementUtils.NAME);
        lastNameTf.setPlaceholder(VaadinElementUtils.LAST_NAME);
        emailTf.setPlaceholder(VaadinElementUtils.EMAIL);

        nameTf.addBlurListener(event -> {
            if (StringUtils.isBlank(nameTf.getValue())) {
                nameTf.setPlaceholder(VaadinElementUtils.NAME);
                nameTf.setCaption("");
                nameTf.setRequiredIndicatorVisible(false);
            }
        });

        nameTf.addFocusListener(event -> {
            nameTf.setPlaceholder("");
            nameTf.setCaption(VaadinElementUtils.NAME);
            nameTf.setRequiredIndicatorVisible(true);
        });

        lastNameTf.addBlurListener(event -> {
            if (StringUtils.isBlank(lastNameTf.getValue())) {
                lastNameTf.setPlaceholder(VaadinElementUtils.LAST_NAME);
                lastNameTf.setCaption("");
                lastNameTf.setRequiredIndicatorVisible(false);
            }
        });

        lastNameTf.addFocusListener(event -> {
            lastNameTf.setPlaceholder("");
            lastNameTf.setCaption(VaadinElementUtils.LAST_NAME);
            lastNameTf.setRequiredIndicatorVisible(true);
        });

        emailTf.addBlurListener(event -> {
            if (StringUtils.isBlank(emailTf.getValue())) {
                emailTf.setPlaceholder(VaadinElementUtils.EMAIL);
                emailTf.setCaption("");
                emailTf.setRequiredIndicatorVisible(false);
            }
        });

        emailTf.addFocusListener(event -> {
            emailTf.setPlaceholder("");
            emailTf.setCaption(VaadinElementUtils.EMAIL);
            emailTf.setRequiredIndicatorVisible(true);
        });

    }

    public void addValidation() {
        binder.forField(nameTf).asRequired(MessagesUtils.MANDATORY_FIELDS).bind(MemberDto::getName, MemberDto::setName);
        binder.forField(lastNameTf).asRequired(MessagesUtils.MANDATORY_FIELDS).bind(MemberDto::getLastName, MemberDto::setLastName);
        binder.forField(emailTf).asRequired(MessagesUtils.MANDATORY_FIELDS)
                .withValidator(new EmailValidator(MessagesUtils.INVALID_EMAIL))
                .bind(MemberDto::getEmail, MemberDto::setEmail);
    }

    public void fillForm(MemberDto selectedMember) {
        nameTf.setValue(selectedMember.getName());
        lastNameTf.setValue(selectedMember.getLastName());
        emailTf.setValue(selectedMember.getEmail());
        nameTf.setCaption(VaadinElementUtils.NAME);
        lastNameTf.setCaption(VaadinElementUtils.LAST_NAME);
        emailTf.setCaption(VaadinElementUtils.EMAIL);
    }

    public void setReadOnly() {
        nameTf.setReadOnly(true);
        lastNameTf.setReadOnly(true);
        emailTf.setReadOnly(true);
    }

    public void removeRequiredIndicators() {
        nameTf.setRequiredIndicatorVisible(false);
        lastNameTf.setRequiredIndicatorVisible(false);
        emailTf.setRequiredIndicatorVisible(false);
    }

    private void addNewMember() {
        MemberDto memberDto = createNewMember();
        try {
            binder.writeBean(memberDto);
            MeetingUI.getMeetingUI().getMemberBean().addMember(memberDto);
            updateMembersGrid();
        } catch (ValidationException e) {
            Message.show(MessagesUtils.ERROR, MessagesUtils.VALIDATION_MESSAGE, EnumUtils.MessageType.ERROR);
        }
    }

    private MemberDto createNewMember() {
        MemberDto memberDto = new MemberDto();
        memberDto.setName(nameTf.getValue());
        memberDto.setLastName(lastNameTf.getValue());
        memberDto.setEmail(emailTf.getValue());
        return memberDto;
    }

    private void updateMember() {
        updateSelectedDomain();
        try {
            binder.writeBean(membersGrid.getSelectedMember());
            MeetingUI.getMeetingUI().getMemberBean().updateMember(membersGrid.getSelectedMember());
            updateMembersGrid();
        } catch (ValidationException e) {
            Message.show(MessagesUtils.ERROR, MessagesUtils.VALIDATION_MESSAGE, EnumUtils.MessageType.ERROR);
        }
    }

    private void updateSelectedDomain() {
        MemberDto selectedMember = membersGrid.getSelectedMember();
        selectedMember.setName(nameTf.getValue());
        selectedMember.setLastName(lastNameTf.getValue());
        selectedMember.setEmail(emailTf.getValue());
    }

    private void deleteMember() {
        MemberDto selectedMember = membersGrid.getSelectedMember();
        MeetingUI.getMeetingUI().getMemberBean().removeMember(selectedMember);
        updateMembersGrid();
    }

    private void updateMembersGrid() {
        membersGrid.getDataProvider().refreshAll();
        membersGrid.deselectAll();
        membersGrid.setSelectedMember(null);
        this.close();
    }

}
