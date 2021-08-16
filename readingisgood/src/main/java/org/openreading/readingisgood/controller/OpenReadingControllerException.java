package org.openreading.readingisgood.controller;

import org.openreading.readingisgood.model.BasicResponse;
import org.springframework.util.Assert;

/**
 * @author Muhammet Sakarya
 * created at 8/15/2021
 */
public class OpenReadingControllerException extends RuntimeException {
    private final BasicResponse basicResponse;


    public OpenReadingControllerException(BasicResponse basicResponse ) {
        this.basicResponse = basicResponse;
    }

    public BasicResponse getBasicResponse() {
        return basicResponse;
    }
}
