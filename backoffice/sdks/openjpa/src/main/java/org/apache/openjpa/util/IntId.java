package org.apache.openjpa.util;

public final class IntId extends OpenJPAId {
    private final int key;

    public IntId(Class var1, Integer var2) {
        this(var1, var2 == null ? 0 : var2);
    }

    public IntId(Class var1, String var2) {
        this(var1, var2 == null ? 0 : Integer.parseInt(var2));
    }

    public IntId(Class var1, int var2) {
        super(var1);
        this.key = var2;
    }

    public IntId(Class var1, int var2, boolean var3) {
        super(var1, var3);
        this.key = var2;
    }

    public int getId() {
        return this.key;
    }

    public Object getIdObject() {
        return this.key;
    }

    public String toString() {
        return String.valueOf(this.key);
    }

    protected int idHash() {
        return this.key;
    }

    protected boolean idEquals(OpenJPAId var1) {
        return this.key == ((IntId)var1).key;
    }
}