package org.openreading.readingisgood.controller;

import org.openreading.readingisgood.model.BasicResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.ResponseExtractor;

import java.io.IOException;
import java.net.URI;
import java.util.List;

/**
 * @author Muhammet Sakarya
 * created at 8/15/2021
 */
//@Component
public class BasicResponseErrorHandler extends DefaultResponseErrorHandler {


    private ResponseExtractor<BasicResponse> basicResponseExtractor;

    public BasicResponseErrorHandler(List<HttpMessageConverter<?>> messageConverters) {
        basicResponseExtractor = new HttpMessageConverterExtractor<>(BasicResponse.class, messageConverters);
    }
    @Override
    public void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {
        this.handleError(response);
    }
    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        BasicResponse basicResponse = null ;
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        try {

            basicResponse =  basicResponseExtractor.extractData(response);
        }
        catch (Exception e) {
            super.handleError(response);
        }
        if (basicResponse != null) {
            throw new OpenReadingControllerException(basicResponse) ;
        }
        else {

            final String message = StringUtils.hasText(response.getStatusText())
                    ? response.getStatusText()
                    : String.valueOf(response.getStatusCode());

            BasicResponse errorResponse = new BasicResponse();
            errorResponse.setCode("1");
            errorResponse.setMessage(message);

            throw new OpenReadingControllerException(basicResponse) ;
        }

     /*   VndErrors vndErrors = null;
        try {
            if (HttpStatus.FORBIDDEN.equals(response.getStatusCode())) {
                vndErrors = new VndErrors(vndErrorExtractor.extractData(response));
            }
            else {
                vndErrors = vndErrorsExtractor.extractData(response);
            }
        }
        catch (Exception e) {
            super.handleError(response);
        }
        if (vndErrors != null) {
            throw new DataFlowClientException(vndErrors);
        }
        else {
            //see https://github.com/spring-cloud/spring-cloud-dataflow/issues/2898
            final String message = StringUtils.hasText(response.getStatusText())
                    ? response.getStatusText()
                    : String.valueOf(response.getStatusCode());

            throw new DataFlowClientException(
                    new VndErrors(String.valueOf(response.getStatusCode()), message));
        }
*/

    }
}
