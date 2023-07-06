package org.designpattern.mediator;

import java.awt.*;

public class ColleagueButton extends Button implements Colleague {
    private Mediator mediator;
    public ColleagueButton(String caption) {
        super(caption);
    }
    public void setMediator(Mediator mediator) {            // Mediator를 보관
        this.mediator = mediator;
    }
    public void setColleagueEnabled(boolean enabled) {      // Mediator가 유효/무효를 지시한다.
        setEnabled(enabled);
    }
}
