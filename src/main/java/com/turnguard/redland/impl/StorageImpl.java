package com.turnguard.redland.impl;

import com.sun.jna.Structure;
import com.sun.jna.TypeMapper;
import com.sun.jna.ptr.PointerByReference;
import com.turnguard.redland.Model;
import com.turnguard.redland.Redland;
import com.turnguard.redland.Storage;
import com.turnguard.redland.utils.GObject;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author http://www.turnguard.com/turnguard
 */
public class StorageImpl extends Structure implements Storage {
    public GObject parent_instance;
    public PointerByReference priv;

    public StorageImpl() {
    }

    public StorageImpl(TypeMapper mapper) {
        super(mapper);
    }

    public StorageImpl(GObject parent_instance, PointerByReference priv) {
        super();
        this.parent_instance = parent_instance;
        this.priv = priv;
    }

    public static class ByReference extends StorageImpl implements Structure.ByReference {
    };

    public static class ByValue extends StorageImpl implements Structure.ByValue {
    };

    @Override
    protected List<?> getFieldOrder() {
        return Arrays.asList("parent_instance", "priv");
    }
    
    public void free(){
        Redland.LIBRDF.librdf_free_storage(this);
    }
    public int open(Model model){
        return Redland.LIBRDF.librdf_storage_open(this, model);
    }
    public int close(){
        return Redland.LIBRDF.librdf_storage_close(this);
    }    
    public int storageSize(){
        return Redland.LIBRDF.librdf_storage_size(this);
    }
}
