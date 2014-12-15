package com.turnguard.redland.impl;

import com.sun.jna.Structure;
import com.sun.jna.TypeMapper;
import com.sun.jna.ptr.PointerByReference;
import com.turnguard.redland.Model;
import com.turnguard.redland.Node;
import com.turnguard.redland.Redland;
import com.turnguard.redland.Statement;
import com.turnguard.redland.Stream;
import com.turnguard.redland.utils.GObject;
import com.turnguard.redland.world.World;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author http://www.turnguard.com/turnguard
 */
public class ModelImpl extends Structure implements Model {
    public GObject parent_instance;
    public PointerByReference priv;

    public ModelImpl() {
    }

    public ModelImpl(TypeMapper mapper) {
        super(mapper);
    }

    public ModelImpl(GObject parent_instance, PointerByReference priv) {
        super();
        this.parent_instance = parent_instance;
        this.priv = priv;
    }

    public static class ByReference extends ModelImpl implements Structure.ByReference {
    };

    public static class ByValue extends ModelImpl implements Structure.ByValue {
    };

    @Override
    protected List<?> getFieldOrder() {
        return Arrays.asList("parent_instance", "priv");
    }
    
    @Override
    public int addStatement(Statement statement) {                              
        return Redland.LIBRDF.librdf_model_add_statement(this, statement);
    }    

    @Override
    public int removeStatement(Statement statement) {
        return Redland.LIBRDF.librdf_model_remove_statement(this, statement);
    }
    
    @Override
    public int add(Node s, Node p, Node o) {
        return Redland.LIBRDF.librdf_model_add(this, s, p, o);
    }
    
    @Override
    public int containsStatement(Statement statement) {
        return Redland.LIBRDF.librdf_model_contains_statement(this, statement);
    }

    @Override
    public void free() {
        Redland.LIBRDF.librdf_free_model(this);
    }

    @Override
    public int modelSize() {
        return Redland.LIBRDF.librdf_model_size(this);
    }

    @Override
    public int addLiteral(Node subject, Node predicate, String label, String language) {
        return this.addLiteral(subject, predicate, label, language,0);
    }

    @Override
    public int addLiteral(Node subject, Node predicate, String label, String language, int is_wf_xml) {
        return Redland.LIBRDF.librdf_model_add_string_literal_statement(this, subject, predicate, label, language, is_wf_xml);
    }

    @Override
    public Stream asStream() {
        return Redland.LIBRDF.librdf_model_as_stream(this);
    }

    @Override
    public int sync() {
        return Redland.LIBRDF.librdf_model_sync(this);
    }
    
}
