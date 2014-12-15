package com.turnguard.redland;

/**
 *
 * @author http://www.turnguard.com/turnguard
 */
public interface Model {
    public int addStatement(Statement statement);
    public int addStatements(Stream stream);
    public int containsStatement(Statement statement);
    public int add(Node s, Node p, Node o);
    public int removeStatement(Statement statement);
    public void free();
    public int modelSize();
    public int addLiteral(Node subject, Node predicate, String label, String language);
    public int addLiteral(Node subject, Node predicate, String label, String language, int is_wf_xml);
    public Stream asStream();
    public int sync();
    public Storage getStorage();
    public int hasArcIn(Node node, Node property);
    public int hasArcOut(Node node, Node property);
    public Stream findStatements(Statement statement);
}
