package com.shift4.request;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RetrieveRequest {

    private Expand expand;

    public RetrieveRequest expand(Expand expand) {
        this.expand = expand;
        return this;
    }

    public Expand getExpand() {
        return expand;
    }
}
