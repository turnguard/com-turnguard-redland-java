package com.turnguard.redland.utils;

import com.sun.jna.Pointer;
import com.sun.jna.PointerType;

/**
 *
 * @author http://www.turnguard.com/turnguard
 */
public class GObject extends PointerType {

    public GObject(Pointer address) {
        super(address);
    }

    public GObject() {
        super();
    }
};
