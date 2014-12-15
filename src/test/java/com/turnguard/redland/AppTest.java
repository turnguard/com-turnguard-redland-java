package com.turnguard.redland;

import com.turnguard.redland.exception.RedlandException;
import com.turnguard.redland.impl.ModelImpl;
import com.turnguard.redland.impl.StatementImpl;
import com.turnguard.redland.impl.StorageImpl;
import com.turnguard.redland.world.World;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        World w = Redland.newWorld();
        System.out.println(w);
    }
    public void testNewStorage(){
        World w = Redland.newWorld();
        Model m;
        Storage s;
        Statement state;
        try {
            w.open();
            s = Redland.newStorage(w, "hashes", "mystorage-name", "new='yes',hash-type='memory',dir='.'");
            m = Redland.newModel(w, s, "");
            
            state = Redland.newStatement(w);
            m.addStatement(state);
            System.out.println("storage: "+s + ", size: " + s.storageSize() + ", contains: " + m.containsStatement(state));
            s.close();
            s.free();
        } catch (RedlandException ex) {
            ex.printStackTrace();
        } 
    }
    public void tesxtNewModel() {
        /*
        System.out.println("testNewModel");
        Model m = Redland.newModel();
        System.out.println("Model: "+m);
        m.free();
        */
    }
    
    public void tesxtNodes(){
        World w = Redland.newWorld();
              w.open();
        Node n = Redland.newLiteral(w, "literal", "en");
        System.out.println(n);
             n = Redland.newURI(w, "http://s.org");
        System.out.println(n);
    }
    
    public void testStatement(){
        World w = Redland.newWorld();
              w.open();
        Statement s = Redland.newStatement(w);
        
        System.out.println("=>"+s.getSubject() + " " + s.getPredicate() + " " + s.getObject());
    }
    
    public void tesxtAddStatementToModel(){
        System.out.println("testAddStatementToModel");

    }
}
