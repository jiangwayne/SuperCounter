package com.plus.server.model;

import java.util.List;

/**
 * Created by mahui on 16/7/26.
 */
public class MDBResponse {
    private String returnCode;
    private String returnMsg;
    private List data;
    public MDBResponse(String returnCode, String returnMsg, List data) {
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
        this.data = data;
    }


}
