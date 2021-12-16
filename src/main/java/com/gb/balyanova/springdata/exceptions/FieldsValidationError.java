package com.gb.balyanova.springdata.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FieldsValidationError {
    private List<String> errorFieldMessage;

    public FieldsValidationError(List<String> errorFieldMessage) {
        this.errorFieldMessage = errorFieldMessage;
    }
}
