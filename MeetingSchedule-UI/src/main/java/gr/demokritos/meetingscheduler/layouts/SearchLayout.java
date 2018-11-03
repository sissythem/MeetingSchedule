package gr.demokritos.meetingscheduler.layouts;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import gr.demokritos.meetingscheduler.applayouts.ContentLayout;
import gr.demokritos.meetingscheduler.comboboxes.SearchCombobox;
import gr.demokritos.meetingscheduler.textfields.SearchField;

public class SearchLayout extends HorizontalLayout {
    private ContentLayout contentLayout;
    private MeetingGridLayout meetingGridLayout;
    private SearchCombobox<String> searchComboBox = new SearchCombobox<>();
    private SearchField searchField = new SearchField(this);
    private HorizontalLayout simpleSearchLayout = new HorizontalLayout();

    public SearchLayout(MeetingGridLayout meetingGridLayout, ContentLayout contentLayout) {
        super();
        setSizeFull();
        setMargin(new MarginInfo(false, true, false, false));
        this.meetingGridLayout = meetingGridLayout;
        this.contentLayout = contentLayout;

        simpleSearchLayout.addComponents(searchComboBox, searchField);
        addComponent(simpleSearchLayout);
        setComponentAlignment(simpleSearchLayout, Alignment.TOP_LEFT);
    }

    public ContentLayout getContentLayout() {
        return contentLayout;
    }

    public void setContentLayout(ContentLayout contentLayout) {
        this.contentLayout = contentLayout;
    }

    public MeetingGridLayout getMeetingGridLayout() {
        return meetingGridLayout;
    }

    public void setMeetingGridLayout(MeetingGridLayout meetingGridLayout) {
        this.meetingGridLayout = meetingGridLayout;
    }

    public SearchCombobox<String> getSearchComboBox() {
        return searchComboBox;
    }

    public void setSearchComboBox(SearchCombobox<String> searchComboBox) {
        this.searchComboBox = searchComboBox;
    }

    public SearchField getSearchField() {
        return searchField;
    }

    public void setSearchField(SearchField searchField) {
        this.searchField = searchField;
    }

    public HorizontalLayout getSimpleSearchLayout() {
        return simpleSearchLayout;
    }

    public void setSimpleSearchLayout(HorizontalLayout simpleSearchLayout) {
        this.simpleSearchLayout = simpleSearchLayout;
    }

}
