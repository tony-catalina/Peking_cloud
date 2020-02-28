package awd.cloud.internet.servers.server.utils;

import org.springframework.http.HttpStatus;

public class ExcepFactor {

    public static final ExcepFactor E_DEFAULT = new ExcepFactor("error.system", HttpStatus.INTERNAL_SERVER_ERROR);

    private String name;
    private HttpStatus httpStatus;

    public ExcepFactor(String name, HttpStatus httpStatus) {
        this.name = name;
        this.httpStatus = httpStatus;
    }

    public String getName() {
        return name;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
