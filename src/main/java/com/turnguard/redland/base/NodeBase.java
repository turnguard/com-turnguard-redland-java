package com.turnguard.redland.base;

import com.sun.jna.Structure;
import com.sun.jna.TypeMapper;
import com.sun.jna.ptr.PointerByReference;
import com.turnguard.redland.Node;
import com.turnguard.redland.Redland;
import com.turnguard.redland.utils.GObject;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author http://www.turnguard.com/turnguard
 */
public class NodeBase extends Structure implements Node {
    public GObject parent_instance;
    public PointerByReference priv;

    public NodeBase() {
    }

    public NodeBase(TypeMapper mapper) {
        super(mapper);
    }

    public NodeBase(GObject parent_instance, PointerByReference priv) {
        super();
        this.parent_instance = parent_instance;
        this.priv = priv;
    }

    public static class ByReference extends NodeBase implements Structure.ByReference {
    };

    public static class ByValue extends NodeBase implements Structure.ByValue {
    };

    @Override
    protected List<?> getFieldOrder() {
        return Arrays.asList("parent_instance", "priv");
    }

    @Override
    public String toString() {
        return Redland.LIBRDF.librdf_node_to_string(this);
    }
    
    @Override
    public String nodeToString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String getLiteralValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
}
