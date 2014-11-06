package com.swamy.exception;

/**
 * Created with IntelliJ IDEA.
 * User: adminuser
 * Date: 10/26/14
 * Time: 5:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class DataNotSavedException extends Throwable {
    public DataNotSavedException(Exception e) {
        super(e);
    }
}
