package gr.demokritos.meetingscheduler.layouts;

import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import gr.demokritos.meetingscheduler.applayouts.ContentLayout;
import gr.demokritos.meetingscheduler.comboboxes.SearchCombobox;
import gr.demokritos.meetingscheduler.interfaces.ButtonsListener;
import gr.demokritos.meetingscheduler.utils.VaadinElementUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class MeetingGridLayout extends VerticalLayout implements ButtonsListener {
    private ContentLayout contentLayout;
    private SearchLayout searchLayout;
    private Grid<?> grid;
    private ButtonsLayout buttonsLayout;

    public MeetingGridLayout(ContentLayout contentLayout) {
        super();
        this.contentLayout = contentLayout;
        this.contentLayout.setMeetingGridLayout(this);
        this.searchLayout = new SearchLayout(this, contentLayout);
        this.buttonsLayout = new ButtonsLayout(this, contentLayout);
    }

    public abstract void setUpColumns();

    public abstract void addValueChangeListenerToSearchComboBox();

    public abstract void setUpSearchField();

    public void setUpLayout(Grid<?> grid) {
        this.grid = grid;
        this.grid.setWidth("100%");
        this.grid.setHeaderRowHeight(45);
        this.grid.setBodyRowHeight(35);
        this.grid.setHeight((Page.getCurrent().getBrowserWindowHeight() * 0.80f) - 30, Unit.PIXELS);
        setUpLayoutInternal();
        setUpSearchComboBox();
        addClickListeners();
    }

    private void setUpLayoutInternal() {
        addComponents(searchLayout, grid, buttonsLayout);
        setComponentAlignment(searchLayout, Alignment.TOP_LEFT);
        setComponentAlignment(grid, Alignment.MIDDLE_CENTER);
        setComponentAlignment(buttonsLayout, Alignment.TOP_LEFT);
    }

    private void setUpSearchComboBox() {
        SearchCombobox<String> searchComboBox = searchLayout.getSearchComboBox();
        List<String> options = new ArrayList<>();
        options.add(VaadinElementUtils.ALL_COLUMNS);
        grid.getColumns().stream().forEach(column -> {
            if (column != grid.getColumn(VaadinElementUtils.INDEX)) {
                options.add(column.getCaption());
            }
        });
        searchComboBox.setEmptySelectionAllowed(false);
        searchComboBox.setItems(options);
        searchComboBox.setValue(VaadinElementUtils.ALL_COLUMNS);
    }

    private void addClickListeners() {
        getButtonsLayout().getDeleteBtn().addClickListener(e -> onDelete());
        getButtonsLayout().getEditBtn().addClickListener(e -> onEdit());
        getButtonsLayout().getViewBtn().addClickListener(e -> onView());
        getButtonsLayout().getAddBtn().addClickListener(e -> onAdd());
    }

    @Override
    public void onAdd() {

    }

    @Override
    public void onDelete() {

    }

    @Override
    public void onEdit() {

    }

    @Override
    public void onView() {

    }

    @Override
    public void onAdvancedSearch() {

    }

    public SearchLayout getSearchLayout() {
        return searchLayout;
    }

    public void setSearchLayout(SearchLayout searchLayout) {
        this.searchLayout = searchLayout;
    }

    public Grid<?> getGrid() {
        return grid;
    }

    public void setGrid(Grid<?> grid) {
        this.grid = grid;
    }

    public ButtonsLayout getButtonsLayout() {
        return buttonsLayout;
    }

    public void setButtonsLayout(ButtonsLayout buttonsLayout) {
        this.buttonsLayout = buttonsLayout;
    }

    public ContentLayout getContentLayout() {
        return contentLayout;
    }

    public void setContentLayout(ContentLayout contentLayout) {
        this.contentLayout = contentLayout;
    }

}
