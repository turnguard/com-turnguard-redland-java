package com.turnguard.redland.impl;

import com.sun.jna.Structure;
import com.sun.jna.TypeMapper;
import com.sun.jna.ptr.PointerByReference;
import com.turnguard.redland.Redland;
import com.turnguard.redland.Statement;
import com.turnguard.redland.Stream;
import com.turnguard.redland.utils.GObject;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author http://www.turnguard.com/turnguard
 */
public class StreamImpl extends Structure implements Stream {
    public GObject parent_instance;
    public PointerByReference priv;

    public StreamImpl() {
    }

    public StreamImpl(TypeMapper mapper) {
        super(mapper);
    }

    public StreamImpl(GObject parent_instance, PointerByReference priv) {
        super();
        this.parent_instance = parent_instance;
        this.priv = priv;
    }

    public static class ByReference extends StreamImpl implements Structure.ByReference {
    };

    public static class ByValue extends StreamImpl implements Structure.ByValue {
    };

    @Override
    protected List<?> getFieldOrder() {
        return Arrays.asList("parent_instance", "priv");
    }

    @Override
    public Statement next() {
        Statement s = Redland.LIBRDF.librdf_stream_get_object(this);
        Redland.LIBRDF.librdf_stream_next(this);
        return s;
    }
}

