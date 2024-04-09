package org.apache.openjpa.util;

public final class Id extends OpenJPAId {
    private final long _id;

    private static final long serialVersionUID = -4715759712231295899L;

    public static Id newInstance(Class var0, Object var1) {
        if (var1 instanceof Id) {
            return (Id)var1;
        } else if (var1 instanceof String) {
            return new Id(var0, (String)var1);
        } else if (var1 instanceof Number) {
            return new Id(var0, ((Number)var1).longValue());
        } else if (var1 == null) {
            return new Id(var0, 0L);
        } else {
            throw new RuntimeException("unknown-oid" + var0 + var1 + var1.getClass());
        }
    }

    public Id(String var1) {
        this(var1, (ClassLoader)null);
    }

    public Id(String var1, ClassLoader var2) {
        if (var2 == null) {
            var2 = (ClassLoader)getClass().getClassLoader();
        }

        if (var1 == null) {
            this._id = 0L;
        } else {
            int var3 = var1.indexOf(45);

            try {
                this.type = Class.forName(var1.substring(0, var3), true, var2);
            } catch (Throwable var5) {
                throw new RuntimeException("string-id" + var1 + var5);
            }

            this._id = Long.parseLong(var1.substring(var3 + 1));
        }

    }

    public Id(Class var1, String var2) {
        super(var1);
        if (var2 == null) {
            this._id = 0L;
        } else {
            int var3 = var2.indexOf(45);
            if (var3 > 0) {
                var2 = var2.substring(var3 + 1);
            }

            this._id = Long.parseLong(var2);
        }

    }

    public Id(Class var1, Long var2) {
        this(var1, var2 == null ? 0L : var2);
    }

    public Id(Class var1, long var2) {
        super(var1);
        this._id = var2;
    }

    public Id(Class var1, long var2, boolean var4) {
        super(var1, var4);
        this._id = var2;
    }

    public long getId() {
        return this._id;
    }

    public Object getIdObject() {
        return Long.valueOf(this._id);
    }

    protected int idHash() {
        return (int)(this._id ^ this._id >>> 32);
    }

    protected boolean idEquals(OpenJPAId var1) {
        return this._id == ((Id)var1)._id;
    }
}