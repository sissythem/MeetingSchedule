package gr.demokritos.meetingscheduler.textfields;

import com.github.appreciated.material.MaterialTheme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.TextField;
import gr.demokritos.meetingscheduler.layouts.SearchLayout;
import gr.demokritos.meetingscheduler.utils.CssUtils;
import gr.demokritos.meetingscheduler.utils.VaadinElementUtils;

public class SearchField extends TextField {

    private SearchLayout searchLayout;

    public SearchField(SearchLayout searchLayout) {
        this.setPlaceholder(VaadinElementUtils.SEARCH);
        this.searchLayout = searchLayout;
        customizeSearchField();
    }

    private void customizeSearchField() {
        this.setIcon(VaadinIcons.SEARCH);
        setStyleName(MaterialTheme.TEXTFIELD_INLINE_ICON);
        addStyleName(CssUtils.SCSS_SEARCH_FIELD);
        addFocusListener(event->addStyleName(MaterialTheme.TEXTFIELD_FLOATING));
        addBlurListener(event->removeStyleName(MaterialTheme.TEXTFIELD_FLOATING));
    }

    public SearchLayout getSearchLayout() {
        return searchLayout;
    }

    public void setSearchLayout(SearchLayout searchLayout) {
        this.searchLayout = searchLayout;
    }

}
