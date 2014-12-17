package com.turnguard.redland.impl;

import com.sun.jna.Structure;
import com.sun.jna.TypeMapper;
import com.sun.jna.ptr.PointerByReference;
import com.turnguard.redland.Node;
import com.turnguard.redland.Redland;
import com.turnguard.redland.Statement;
import com.turnguard.redland.base.NodeBase;
import com.turnguard.redland.utils.GObject;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author http://www.turnguard.com/turnguard
 */
public class StatementImpl extends Structure implements Statement {
    public GObject parent_instance;
    public PointerByReference priv;

    public StatementImpl() {
    }

    public StatementImpl(TypeMapper mapper) {
        super(mapper);
    }

    public StatementImpl(GObject parent_instance, PointerByReference priv) {
        super();
        this.parent_instance = parent_instance;
        this.priv = priv;
    }

    public static class ByReference extends StatementImpl implements Structure.ByReference {
    };

    public static class ByValue extends StatementImpl implements Structure.ByValue {
    };

    @Override
    protected List<?> getFieldOrder() {
        return Arrays.asList("parent_instance", "priv");
    }
    
    public void init(WorldImpl world){
        Redland.LIBRDF.librdf_statement_init(world, this);
    }
    
    @Override
    public Node getSubject(){
        return Redland.LIBRDF.librdf_statement_get_subject(this);
    }
    @Override
    public Node getPredicate(){
        return Redland.LIBRDF.librdf_statement_get_predicate(this);
    }
    @Override
    public Node getObject(){
        return Redland.LIBRDF.librdf_statement_get_object(this);
    }    
}
