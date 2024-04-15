package org.apache.openjpa.util;

public final class StringId extends OpenJPAId {
    private final String key;

    public StringId(Class var1, String var2) {
        super(var1);
        this.key = var2 == null ? "" : var2;
    }

    public StringId(Class var1, String var2, boolean var3) {
        super(var1, var3);
        this.key = var2 == null ? "" : var2;
    }

    public String getId() {
        return this.key;
    }

    public Object getIdObject() {
        return this.key;
    }

    public String toString() {
        return this.key;
    }

    protected int idHash() {
        return this.key.hashCode();
    }

    protected boolean idEquals(OpenJPAId var1) {
        return this.key.equals(((StringId)var1).key);
    }
}