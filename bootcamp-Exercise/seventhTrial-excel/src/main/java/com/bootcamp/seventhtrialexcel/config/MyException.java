package com.bootcamp.seventhtrialexcel.config;

import lombok.Setter;

// 클론 코딩(...) 중이지만 굳이 Custom Exception을 만든 이유는 잘 모르겠습니다.
public class MyException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private String errorCode;

    // This message is the key in the properties file
    private boolean propertiesKey = true;

    public MyException(String message) {
        super(message);
    }

    public MyException(String errorCode, String message) {
        this(errorCode, message, true);
    }

    public MyException(String errorCode, String message, boolean propertiesKey) {
        super(message);
        this.setErrorCode(errorCode);
        this.setPropertiesKey(propertiesKey);
    }

    public MyException(String errorCode, String message, Throwable cause, boolean propertiesKey) {
        super(message, cause);
        this.setErrorCode(errorCode);
        this.setPropertiesKey(propertiesKey);
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isPropertiesKey() {
        return propertiesKey;
    }
    public void setPropertiesKey(boolean propertiesKey) {
        this.propertiesKey = propertiesKey;
    }
}
