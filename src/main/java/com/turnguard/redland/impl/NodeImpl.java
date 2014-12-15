package com.turnguard.redland.impl;

import com.turnguard.redland.base.NodeBase;
import com.sun.jna.Structure;
import com.sun.jna.TypeMapper;
import com.sun.jna.ptr.PointerByReference;
import com.turnguard.redland.utils.GObject;
import com.turnguard.redland.world.World;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author http://www.turnguard.com/turnguard
 */
public class NodeImpl extends NodeBase {

    public NodeImpl() {
        super();
    }

    public NodeImpl(TypeMapper mapper) {
        super(mapper);
    }

    public NodeImpl(GObject parent_instance, PointerByReference priv) {
        super(parent_instance, priv);
    }

    public static class ByReference extends NodeImpl implements Structure.ByReference {
    };

    public static class ByValue extends NodeImpl implements Structure.ByValue {
    };

    @Override
    protected List<?> getFieldOrder() {
        return Arrays.asList("parent_instance", "priv");
    }
}
