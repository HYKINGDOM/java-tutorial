package com.java.data.controller.validator;

import javax.validation.Payload;

public class Severity {

    public interface Info extends Payload {
    }

    public interface Warn extends Payload {
    }

    public interface Error extends Payload {
    }

}
