package com.turnguard.redland;

/**
 *
 * @author http://www.turnguard.com/turnguard
 */
public interface Stream {
    public Statement getObject();
    public void free();
    public int next();
}
