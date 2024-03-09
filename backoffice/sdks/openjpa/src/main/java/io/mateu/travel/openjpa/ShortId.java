package io.mateu.travel.openjpa;

public final class ShortId extends OpenJPAId {
    private final short key;

    public ShortId(Class var1, Short var2) {
        this(var1, var2 == null ? 0 : var2);
    }

    public ShortId(Class var1, String var2) {
        this(var1, var2 == null ? 0 : Short.parseShort(var2));
    }

    public ShortId(Class var1, short var2) {
        super(var1);
        this.key = var2;
    }

    public ShortId(Class var1, short var2, boolean var3) {
        super(var1, var3);
        this.key = var2;
    }

    public short getId() {
        return this.key;
    }

    public Object getIdObject() {
        return this.key;
    }

    public String toString() {
        return Short.toString(this.key);
    }

    protected int idHash() {
        return this.key;
    }

    protected boolean idEquals(OpenJPAId var1) {
        return this.key == ((ShortId)var1).key;
    }
}