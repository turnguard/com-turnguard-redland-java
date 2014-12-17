package com.turnguard.redland;

import com.turnguard.redland.exception.RedlandException;
import com.turnguard.redland.impl.WorldImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author turnguard
 */
@FixMethodOrder(MethodSorters.JVM)
public class ModelTest {
    
    private static World world;
    private static Storage storage;
    private static Model model;
    public ModelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws RedlandException {
        System.out.println("setUpClass");
        world = Redland.newWorld();
        world.open();
        storage = Redland.newStorage(world, "hashes", "mystorage-name", "new='yes',hash-type='memory',dir='/tmp/xxx/'");
        model = Redland.newModel(world, storage, "");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("sync: " + model.sync());
        System.out.println("tearDownClass");
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAddStatement(){        
        Statement state = Redland.newStatement(world);
        model.addStatement(state);
        System.out.println(storage.storageSize());
    }
    
    @Test
    public void testStreamModel1(){  
        Stream stream = model.asStream();
        Statement s;
        while((s = stream.getObject())!=null){
            System.out.println("testStreamModel1 nextSubject: "+s.getSubject());
            stream.next();
        }
    }
    
    @Test
    public void testContainsStatement(){
        Statement state = Redland.newStatement(world);
        System.out.println(model.containsStatement(state));        
    }
    @Test
    public void testContainsStatement1(){
        Statement state = Redland.newStatement(world, 
                Redland.newURI(world, "http://xxx-s.org"),
                Redland.newURI(world, "http://xxx-p.org"),
                Redland.newURI(world, "http://xxx-o.org"));
        model.addStatement(state);
        System.out.println(model.containsStatement(state));        
    }
    
    @Test
    public void testParseIntoModel(){
        Parser p = Redland.newParser(world, "turtle", "text/turtle", null);       
        System.out.println(p);
        
        //Stream s = p.parseAsStream(Redland.newURI(world, "/home/turnguard/xx.ttl"), Redland.newURI(world, "file:///home/turnguard/"));
        //System.out.println("stream " + s);
        model.addStatements(p.parseAsStream(Redland.newURI(world, "file:///home/turnguard/xx.ttl"), Redland.newURI(world, "file:///home/turnguard/")));
        /*
        p.parseIntoModel(
                Redland.newURI(world, "file:///home/turnguard/xx.ttl"), 
                null, model);
        */
        Stream stream = model.asStream();
        Statement s;
        while((s = stream.getObject())!=null){
            System.out.println("testParseIntoModel nextSubject: "+s.getSubject());
            stream.next();
        }   
        stream.free();
    }
    
    @Test
    public void testStreamModel2(){  
        Stream stream = model.asStream();
        Statement s;
        while((s = stream.getObject())!=null){
            System.out.println("testStreamModel2 nextSubject: "+s.getSubject());
            stream.next();
        }
    }
    
    @Test
    public void testRemoveStatement(){
        Statement state = Redland.newStatement(world);
        model.removeStatement(state);
        System.out.println(storage.storageSize());
    }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
