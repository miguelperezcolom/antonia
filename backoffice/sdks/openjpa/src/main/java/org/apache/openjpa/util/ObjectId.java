package org.apache.openjpa.util;

public final class ObjectId extends OpenJPAId {
    private Object _key;

    public ObjectId(Class var1, Object var2) {
        super(var1);
        this._key = var2;
    }

    public ObjectId(Class var1, Object var2, boolean var3) {
        super(var1, var3);
        this._key = var2;
    }

    public Object getId() {
        return this._key;
    }

    void setId(Object var1) {
        this._key = var1;
    }

    public Object getIdObject() {
        return this._key;
    }

    protected int idHash() {
        return this._key == null ? 0 : this._key.hashCode();
    }

    protected boolean idEquals(OpenJPAId var1) {
        Object var2 = ((ObjectId)var1)._key;
        return this._key == null ? var2 == null : this._key.equals(var2);
    }
}