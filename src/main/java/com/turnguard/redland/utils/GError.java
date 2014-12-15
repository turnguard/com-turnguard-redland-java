package com.turnguard.redland.utils;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author http://www.turnguard.com/turnguard
 */
public class GError extends Structure {

    public volatile int domain;
    public volatile int code;
    public volatile String message;

    public GError(Pointer address) {
        useMemory(address, 0);
    }

    @Override
    protected List getFieldOrder() {
        return Arrays.asList("domain", "code", "message");
    }

    /**
     * Retrieve tracker's error code
     *
     * @return int tracker's error code
     */
    public int getCode() {
        return (Integer) readField("code");
    }

    /**
     * Retrieve tracker's error message
     *
     * @return String tracker's error message
     */
    public String getMessage() {
        return (String) readField("message");
    }

    public static class ByReference extends GError implements Structure.ByReference {

        public ByReference(Pointer address) {
            super(address);
        }
    };

    public static class ByValue extends GError implements Structure.ByValue {

        public ByValue(Pointer address) {
            super(address);
        }
    };
}
