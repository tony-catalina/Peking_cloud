package awd.cloud.internet.servers.server.utils;

import org.springframework.http.HttpStatus;


public class UserExcepFactor {
    public static final ExcepFactor AUTH_FAILED = new ExcepFactor("error.auth.failed", HttpStatus.NOT_ACCEPTABLE);
    public static final ExcepFactor DELETE_SUPER_FAILED = new ExcepFactor("error.delete.super.failed", HttpStatus.NOT_ACCEPTABLE);
    public static final ExcepFactor DELETE_PARENT_FAILED = new ExcepFactor("error.delete.parent.failed", HttpStatus.NOT_ACCEPTABLE);
    public static final ExcepFactor DELETE_NODE_USER_FAILED = new ExcepFactor("error.delete.node.user.failed", HttpStatus.NOT_ACCEPTABLE);
    public static final ExcepFactor ERROR_RECORD_IS_EXIST = new ExcepFactor("error.record.is.exist", HttpStatus.NOT_ACCEPTABLE);
    public static final ExcepFactor ERROR_RECORD_NOT_EXIST = new ExcepFactor("error.record.not.exist", HttpStatus.NOT_ACCEPTABLE);

}
