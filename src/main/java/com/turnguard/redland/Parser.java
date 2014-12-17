package com.turnguard.redland;

/**
 *
 * @author http://www.turnguard.com/turnguard
 */
public interface Parser {
    public void free(World world);
    public int parseIntoModel(URI uri, URI baseURI, Model model);
    public Stream parseAsStream(URI uri, URI baseURI);
    public Stream parseStringAsStream(String string, URI baseURI);
    
}
