package com.turnguard.redland;

import com.turnguard.redland.impl.ModelImpl;

/**
 *
 * @author http://www.turnguard.com/turnguard
 */
public interface Storage {
    public void free();
    public int open(Model model);
    public int close();
    public int storageSize();
}
