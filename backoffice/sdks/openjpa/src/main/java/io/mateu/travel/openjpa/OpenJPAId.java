package io.mateu.travel.openjpa;

import org.springframework.util.ConcurrentReferenceHashMap;

import java.io.Serializable;

public abstract class OpenJPAId implements Comparable, Serializable {

    private static ConcurrentReferenceHashMap _typeCache = new ConcurrentReferenceHashMap(1, 0);

    protected Class type;
    protected boolean subs = true;
    private transient int _typeHash = 0;

    protected OpenJPAId() {
    }

    protected OpenJPAId(Class type) {
        this.type = type;
    }

    protected OpenJPAId(Class type, boolean subs) {
        this.type = type;
        this.subs = subs;
    }

    public Class getType() {
        return this.type;
    }

    public boolean hasSubclasses() {
        return this.subs;
    }

    public void setManagedInstanceType(Class var1) {
        this.type = var1;
        this.subs = false;
    }

    public void setManagedInstanceType(Class var1, boolean var2) {
        this.type = var1;
        this.subs = var2;
    }

    public abstract Object getIdObject();

    protected abstract int idHash();

    protected abstract boolean idEquals(OpenJPAId var1);

    public int hashCode() {
        if (this._typeHash == 0) {
            Integer var1 = this.type.hashCode();
            if (var1 == null) {
                Class var2 = this.type;

                for(Class var3 = var2.getSuperclass(); var3 != null && var3 != Object.class; var3 = var2.getSuperclass()) {
                    var2 = var2.getSuperclass();
                }

                this._typeHash = var2.hashCode();
                _typeCache.put(this.type, this._typeHash);
            } else {
                this._typeHash = var1;
            }
        }

        return this._typeHash ^ this.idHash();
    }

    public boolean equals(Object var1) {
        if (var1 == this) {
            return true;
        } else if (var1 != null && this.getClass() == var1.getClass()) {
            OpenJPAId var2 = (OpenJPAId)var1;
            return this.idEquals(var2) && (var2.type.isAssignableFrom(this.type) || this.subs && this.type.isAssignableFrom(var2.type));
        } else {
            return false;
        }
    }

    public String toString() {
        return this.type.getName() + "-" + this.getIdObject();
    }

    public int compareTo(Object var1) {
        if (var1 == this) {
            return 0;
        } else {
            return var1 == null ? 1 : ((Comparable)this.getIdObject()).compareTo(((OpenJPAId)var1).getIdObject());
        }
    }
}
