package com.turnguard.redland.impl;

import com.turnguard.redland.base.NodeBase;
import com.sun.jna.Structure;
import com.sun.jna.TypeMapper;
import com.sun.jna.ptr.PointerByReference;
import com.turnguard.redland.URI;
import com.turnguard.redland.utils.GObject;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author http://www.turnguard.com/turnguard
 */
public class URIImpl extends NodeBase implements URI {

    public URIImpl() {
        super();
    }

    public URIImpl(TypeMapper mapper) {
        super(mapper);
    }

    public URIImpl(GObject parent_instance, PointerByReference priv) {
        super(parent_instance, priv);        
    }

    public static class ByReference extends URIImpl implements Structure.ByReference {
    };

    public static class ByValue extends URIImpl implements Structure.ByValue {
    };
    
    @Override
    protected List<?> getFieldOrder() {
        return Arrays.asList("parent_instance", "priv");
    }
}
