package com.turnguard.redland;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author turnguard
 */
public class StreamTest {
    
    public StreamTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void hello() {
        World world = Redland.newWorld();
        Parser p = Redland.newParser(world, "turtle", "text/turtle", null);       
        System.out.println(p);
        
        //Stream s = p.parseAsStream(Redland.newURI(world, "/home/turnguard/xx.ttl"), Redland.newURI(world, "file:///home/turnguard/"));
        //System.out.println("stream " + s);
        /*
               p.parseIntoModel(
                       Redland.newURI(world, "file:///home/turnguard/xx.ttl"), 
                       Redland.newURI(world, "urn:home/turnguard"), model);
        */
        Stream stream = p.parseStringAsStream("<http://s.net/01> a <http://www.w3.org/2004/02/skos/core#Concept> .", Redland.newURI(world, "urn:home/turnguard"));
        //System.out.println(stream.getObject().getSubject());
        
        Statement s;
        while((s = stream.getObject())!=null){
            System.out.println("testParseIntoModel nextSubject: "+s.getSubject());
            stream.next();
        }   
        
        stream.free();
    }
}
