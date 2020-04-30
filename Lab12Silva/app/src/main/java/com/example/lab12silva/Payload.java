package com.example.lab12silva;

public class Payload {

    private Object args, headers;
    private String origin, url;

    public Object getArgs() { return this.args; }
    public void setArgs (Object args) { this.args = args; }

    public Object getHeaders() { return this.headers; }
    public void setHeaders (Object headers) { this.headers = headers; }

    public String getOrigin() { return this.origin; }
    public void setOrigin (String origin) { this.origin = origin; }

    public String getUrl() { return this.url; }
    public void setUrl (String url) { this.url = url; }
}
