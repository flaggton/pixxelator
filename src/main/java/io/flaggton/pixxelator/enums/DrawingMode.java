package io.flaggton.pixxelator.enums;

import lombok.Getter;

@Getter
public enum DrawingMode {

    UNSET("Unset"),
    PENCIL("Pencil"),
    FILL_ALL("Fill all"),
    REPLACE_PIXEL_COLOR("Replace pixel color"),
    STANDARD_BUCKET("Standard bucket");

    private final String uiText;

    DrawingMode(String uiText) {
        this.uiText = uiText;
    }

}