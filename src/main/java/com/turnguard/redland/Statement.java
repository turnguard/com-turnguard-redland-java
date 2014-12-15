package com.turnguard.redland;

/**
 *
 * @author http://www.turnguard.com/turnguard
 */
public interface Statement {
    public Node getSubject();
    public Node getPredicate();
    public Node getObject();
}
