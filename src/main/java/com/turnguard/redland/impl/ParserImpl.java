package com.turnguard.redland.impl;

import com.sun.jna.Structure;
import com.sun.jna.TypeMapper;
import com.sun.jna.ptr.PointerByReference;
import com.turnguard.redland.Model;
import com.turnguard.redland.Parser;
import com.turnguard.redland.Redland;
import com.turnguard.redland.Stream;
import com.turnguard.redland.URI;
import com.turnguard.redland.World;
import com.turnguard.redland.utils.GObject;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author http://www.turnguard.com/turnguard
 */
public class ParserImpl extends Structure implements Parser {

    public GObject parent_instance;
    public PointerByReference priv;

    public ParserImpl() {
    }

    public ParserImpl(TypeMapper mapper) {
        super(mapper);
    }

    public ParserImpl(GObject parent_instance, PointerByReference priv) {
        super();
        this.parent_instance = parent_instance;
        this.priv = priv;
    }

    public static class ByReference extends ParserImpl implements Structure.ByReference {
    };

    public static class ByValue extends ParserImpl implements Structure.ByValue {
    };

    @Override
    protected List<?> getFieldOrder() {
        return Arrays.asList("parent_instance", "priv");
    }

    @Override
    public void free(World world) {
        Redland.LIBRDF.librdf_free_parser(world, this);
    }

    @Override
    public int parseIntoModel(URI uri, URI baseURI, Model model) {
        return Redland.LIBRDF.librdf_parser_parse_into_model(this, uri, baseURI, model);
    }

    @Override
    public Stream parseAsStream(URI uri, URI baseURI) {
        return Redland.LIBRDF.librdf_parser_parse_as_stream(this, uri, baseURI);
    }

    @Override
    public Stream parseStringAsStream(String string, URI baseURI) {
        return Redland.LIBRDF.librdf_parser_parse_string_as_stream(this, string, baseURI);
    }
}
