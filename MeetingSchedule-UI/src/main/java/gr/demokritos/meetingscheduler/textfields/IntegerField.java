package gr.demokritos.meetingscheduler.textfields;

import com.vaadin.data.HasValue;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.TextField;

public class IntegerField extends TextField implements HasValue.ValueChangeListener {

    String lastValue;

    public IntegerField(String caption) {
        super(caption);
        setResponsive(true);
        setValueChangeMode(ValueChangeMode.EAGER);
        addValueChangeListener(this);
    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        if (event.getValue() != null) {
            String text = event.getValue().toString();
            try {
                new Integer(text);
                lastValue = text;
            } catch (NumberFormatException e) {
                setValue(lastValue);
            }
        }
    }
}
