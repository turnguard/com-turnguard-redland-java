package com.turnguard.redland.impl;

import com.sun.jna.Structure;
import com.sun.jna.TypeMapper;
import com.sun.jna.ptr.PointerByReference;
import com.turnguard.redland.Redland;
import com.turnguard.redland.World;
import com.turnguard.redland.utils.GObject;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author http://www.turnguard.com/turnguard
 */
public class WorldImpl extends Structure implements World {

    public GObject parent_instance;
    public PointerByReference priv;

    public WorldImpl() {
    }

    public WorldImpl(TypeMapper mapper) {
        super(mapper);
    }

    public WorldImpl(GObject parent_instance, PointerByReference priv) {
        super();
        this.parent_instance = parent_instance;
        this.priv = priv;
    }

    public static class ByReference extends WorldImpl implements Structure.ByReference {
    };

    public static class ByValue extends WorldImpl implements Structure.ByValue {
    };

    @Override
    protected List<?> getFieldOrder() {
        return Arrays.asList("parent_instance", "priv");
    }
    
    public void open(){
        Redland.LIBRDF.librdf_world_open(this);
    }
}
