package com.swamy.exception;

/**
 * Created with IntelliJ IDEA.
 * User: adminuser
 * Date: 10/25/14
 * Time: 5:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class DBNotAvailableException extends Throwable {
    public DBNotAvailableException(String s) {
        super(s);
    }
}
