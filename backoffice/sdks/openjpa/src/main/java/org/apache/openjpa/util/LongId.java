package org.apache.openjpa.util;

public final class LongId extends OpenJPAId {

    private static final long serialVersionUID = -5934151979593562005L;

    private final long key;

    public LongId(Class var1, Long var2) {
        this(var1, var2 == null ? 0L : var2);
    }

    public LongId(Class var1, String var2) {
        this(var1, var2 == null ? 0L : Long.parseLong(var2));
    }

    public LongId(Class var1, long var2) {
        super(var1);
        this.key = var2;
    }

    public LongId(Class var1, long var2, boolean var4) {
        super(var1, var4);
        this.key = var2;
    }

    public long getId() {
        return this.key;
    }

    public Object getIdObject() {
        return this.key;
    }

    protected int idHash() {
        return (int)(this.key ^ this.key >>> 32);
    }

    protected boolean idEquals(OpenJPAId var1) {
        return this.key == ((LongId)var1).key;
    }
}
