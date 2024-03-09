package io.mateu.travel.openjpa;

public final class ShortId extends OpenJPAId {
    private final short key;

    public ShortId(Class type, String id) {
        this(type, id == null ? 0 : Short.parseShort(id));
    }

    public ShortId(Class type, short id) {
        super(type);
        this.key = id;
    }

    public ShortId(Class type, short id, boolean subs) {
        super(type, subs);
        this.key = id;
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