package com.turnguard.redland;

/**
 *
 * @author http://www.turnguard.com/turnguard
 */
public interface Node {
    //public String getBlankIdentifier();
    //public String getCountedBlankIdentifier();
    //public int getLiOrdinal();
    public String getLiteralValue();
    //public String getLiteralValueAsCountedString();
    public String nodeToString();
}
