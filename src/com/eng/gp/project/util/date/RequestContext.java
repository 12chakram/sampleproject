package com.eng.gp.project.util.date;

import java.io.Serializable;
import java.util.UUID;

public class RequestContext implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = -1141749336491836646L;

    private String scheme;

    private String serverName;

    private String remoteAddress;

    private int serverPort;

    private String contextPath;

    private final String requestId;

    public RequestContext() {
        requestId = UUID.randomUUID().toString();
    }

    public String getId() {
        return requestId;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    @Override
    public int hashCode() {
        return requestId.hashCode();
    }
}
