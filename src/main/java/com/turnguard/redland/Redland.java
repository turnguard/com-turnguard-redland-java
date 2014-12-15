package com.turnguard.redland;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.PointerType;
import com.sun.jna.Structure;
import com.sun.jna.TypeMapper;
import com.sun.jna.ptr.PointerByReference;
import com.turnguard.redland.exception.RedlandException;
import com.turnguard.redland.impl.ModelImpl;
import com.turnguard.redland.impl.NodeImpl;
import com.turnguard.redland.base.NodeBase;
import com.turnguard.redland.impl.URIImpl;
import com.turnguard.redland.impl.StatementImpl;
import com.turnguard.redland.impl.StorageImpl;
import com.turnguard.redland.impl.StreamImpl;
import com.turnguard.redland.utils.GError;
import com.turnguard.redland.world.World;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author http://www.turnguard.com/turnguard
 */
public class Redland {
    
    public static final String REDLAND_LIBRDF = "librdf";    
    public static final Redland.LibRDF LIBRDF;
    public static final Map<String, Object> options = new HashMap<>();
    
    static {
        //options.put(Library.OPTION_TYPE_MAPPER, new TrackerSparqlValueTypeMapper());
        LIBRDF = (Redland.LibRDF)Native.loadLibrary(REDLAND_LIBRDF, Redland.LibRDF.class, options);
    };
    


    public interface LibRDF extends Library {
        public World librdf_new_world();
        public void librdf_free_world(World world);
        public void librdf_world_open(World world);
        public StorageImpl librdf_new_storage(World world, String storageName, String name, String options);
        public void librdf_free_storage(Storage storage);
        public int librdf_storage_open(Storage storage, Model model);
        public int librdf_storage_close(Storage storage);
        public int librdf_storage_size(Storage storage);
        /* MODEL */
        public ModelImpl librdf_new_model(World world, Storage storage, String options);
        public int librdf_model_add_statement(Model model, Statement state);
        public int librdf_model_remove_statement(Model model, Statement state);
        public int librdf_model_add(Model model, Node s, Node p, Node o);
        public int librdf_model_contains_statement(Model model, Statement state);
        public void librdf_free_model(Model model);
        public int librdf_model_size(Model model);
        public int librdf_model_add_string_literal_statement(Model model, Node subject, Node predicate, String label, String language, int is_wf_xml);
        public int librdf_model_add_string_literal_statement(Model model, Node subject, Node predicate, String label, String language);
        public StreamImpl librdf_model_as_stream(Model model);
        public int librdf_model_sync(Model model);
        /* STREAM */
        public int librdf_stream_end(Stream stream);
        public int librdf_free_stream(Stream stream);
        public int librdf_stream_next(Stream stream);
        public StatementImpl librdf_stream_get_object(Stream stream);
        /* STATEMENTS */
        public StatementImpl librdf_new_statement(World world);
        public StatementImpl librdf_new_statement_from_statement(StatementImpl statement);
        public StatementImpl librdf_new_statement_from_statement2(StatementImpl statement);
        public StatementImpl librdf_new_statement_from_nodes(World world, Node subject, Node predicate, Node object);
        public void librdf_statement_init(World world, StatementImpl statement);
        /* NODES */
        public NodeBase librdf_new_node(World w);
        public NodeBase librdf_new_node_from_literal(World w, String label, String xmlLanguage, int is_wf_xml);
        public URIImpl librdf_new_node_from_uri_string(World w, String uriString);
        public URIImpl librdf_new_uri(World w, String uriString);
        
        public NodeBase librdf_statement_get_subject(StatementImpl s);
        public NodeBase librdf_statement_get_predicate(StatementImpl s);
        public NodeBase librdf_statement_get_object(StatementImpl s);

        public String librdf_node_get_literal_value(NodeBase node);
        public String librdf_node_to_string(NodeBase node);
    }
   
    
    public static World newWorld() {
        PointerByReference error = new PointerByReference(null);
        World world = Redland.LIBRDF.librdf_new_world();
        return world;
    }
    public static void freeWorld(World world){
        Redland.LIBRDF.librdf_free_world(world);
    }
    public static void openWorld(World world){
        Redland.LIBRDF.librdf_world_open(world);
    }    
    
    public static Model newModel(World world, Storage storage, String options){
        Model m = Redland.LIBRDF.librdf_new_model(world, storage, options);
        return m;
    }
    public static Node newLiteral(World world, String label, String xmlLang){
        return Redland.LIBRDF.librdf_new_node_from_literal(world, label, xmlLang, 0);
    }
    
    public static URI newURI(World world, String uriString){
        return (URI)Redland.LIBRDF.librdf_new_node_from_uri_string(world, uriString);
    }
    
    public static Statement newStatement(World world){
        return Redland.LIBRDF.librdf_new_statement_from_nodes(
                world, 
                Redland.newURI(world, "http://www.s.com"),
                Redland.newURI(world, "http://www.p.com"),
                Redland.newURI(world, "http://www.o.com")
        );
    }
    
    public static Statement newStatement(World world, Node subject, Node predicate, Node object){
        return Redland.LIBRDF.librdf_new_statement_from_nodes(
                world, 
                subject,
                predicate,
                object
        );
    }
    
    public static StorageImpl newStorage(World world, String storageName, String name, String options) throws RedlandException{
        PointerByReference error = new PointerByReference(null);
        StorageImpl s = Redland.LIBRDF.librdf_new_storage(world, storageName, name, options);
        if(error.getValue()!=null){
            throw new RedlandException(new GError(error.getValue()));
        }
        return s;
    }    
}
