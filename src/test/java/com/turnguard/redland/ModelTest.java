package com.turnguard.redland;

import com.turnguard.redland.exception.RedlandException;
import com.turnguard.redland.world.World;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
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
        while((s = stream.next())!=null){
            System.out.println("testStreamModel1 nextSubject: "+s.getSubject());
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
    public void testStreamModel2(){  
        Stream stream = model.asStream();
        Statement s;
        while((s = stream.next())!=null){
            System.out.println("testStreamModel2 nextSubject: "+s.getSubject());
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
