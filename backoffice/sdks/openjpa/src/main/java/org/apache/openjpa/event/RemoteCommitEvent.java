package org.apache.openjpa.event;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RemoteCommitEvent implements Externalizable {
    private static final long serialVersionUID = 1L;
    public static final int PAYLOAD_OIDS = 0;
    public static final int PAYLOAD_OIDS_WITH_ADDS = 1;
    public static final int PAYLOAD_EXTENTS = 2;
    public static final int PAYLOAD_LOCAL_STALE_DETECTION = 3;
    public static final int PAYLOAD_SALUDO = 1000;
    private int _payload = 0;
    private Collection _addIds = null;
    private Collection _addClasses = null;
    private Collection _updates = null;
    private Collection _deletes = null;
    private String _sourceId = null;
    private boolean saludo = false;
    private String _senderIp = null;

    public boolean isSaludo() {
        return this._payload == 1000;
    }

    public void setSaludo(boolean var1) {
        this._payload = 1000;
    }

    public RemoteCommitEvent() {
    }

    public RemoteCommitEvent(int var1, Collection var2, Collection var3, Collection var4, Collection var5) {
        this._payload = var1;
        if (var2 != null) {
            this._addIds = Collections.unmodifiableCollection(var2);
        }

        if (var3 != null) {
            this._addClasses = Collections.unmodifiableCollection(var3);
        }

        if (var4 != null) {
            this._updates = Collections.unmodifiableCollection(var4);
        }

        if (var5 != null) {
            this._deletes = Collections.unmodifiableCollection(var5);
        }

    }

    /**
     *
     * @param payload  this._transmitPersIds ? 1 : 0;       var2.isTrackChangesByType() --> 2
     * @param addIds
     * @param addClasses
     * @param updates object ids --> clase + id
     * @param deletes
     * @param sourceId
     * @param senderIp
     */
    public RemoteCommitEvent(int payload, Collection addIds, Collection addClasses, Collection updates, Collection deletes, String sourceId, String senderIp) {
        this._payload = payload;
        if (addIds != null) {
            this._addIds = Collections.unmodifiableCollection(addIds);
        }

        if (addClasses != null) {
            this._addClasses = Collections.unmodifiableCollection(addClasses);
        }

        if (updates != null) {
            this._updates = Collections.unmodifiableCollection(updates);
        }

        if (deletes != null) {
            this._deletes = Collections.unmodifiableCollection(deletes);
        }

        this._sourceId = sourceId;
        this._senderIp = senderIp;
    }

    public int getPayloadType() {
        return this._payload;
    }

    public Collection getPersistedObjectIds() {
        if (this._payload != 1) {
            if (this._payload == 0) {
                throw new RuntimeException("no-added-oids");
            } else {
                throw new RuntimeException("extent-only-event");
            }
        } else {
            return (Collection)(this._addIds == null ? Collections.EMPTY_LIST : this._addIds);
        }
    }

    public Collection getUpdatedObjectIds() {
        if (this._payload == 2) {
            throw new RuntimeException("extent-only-event");
        } else {
            return (Collection)(this._updates == null ? Collections.EMPTY_LIST : this._updates);
        }
    }

    public Collection getDeletedObjectIds() {
        if (this._payload == 2) {
            throw new RuntimeException("extent-only-event");
        } else {
            return (Collection)(this._deletes == null ? Collections.EMPTY_LIST : this._deletes);
        }
    }

    public Collection getPersistedTypeNames() {
        return (Collection)(this._addClasses == null ? Collections.EMPTY_LIST : this._addClasses);
    }

    public Collection getUpdatedTypeNames() {
        if (this._payload != 2) {
            throw new RuntimeException("nonextent-event");
        } else {
            return (Collection)(this._updates == null ? Collections.EMPTY_LIST : this._updates);
        }
    }

    public Collection getDeletedTypeNames() {
        if (this._payload != 2) {
            throw new RuntimeException("nonextent-event");
        } else {
            return (Collection)(this._deletes == null ? Collections.EMPTY_LIST : this._deletes);
        }
    }

    public String getSourceId() {
        return this._sourceId;
    }

    public String getSenderIp() {
        return this._senderIp;
    }

    public void writeExternal(ObjectOutput var1) throws IOException {
        var1.writeInt(this._payload);
        var1.writeObject(this._addClasses);
        if (this._payload == 1) {
            var1.writeObject(this._addIds);
        }

        var1.writeObject(this._updates);
        var1.writeObject(this._deletes);
        if (this._sourceId != null) {
            var1.writeUTF(this._sourceId);
        } else {
            var1.writeUTF("");
        }

        if (this._senderIp != null) {
            var1.writeUTF(this._senderIp);
        } else {
            var1.writeUTF("");
        }

    }

    public void readExternal(ObjectInput var1) throws IOException {
        try {
            this._payload = var1.readInt();
            this._addClasses = (Collection)var1.readObject();
            if (this._payload == 1) {
                this._addIds = (Collection)var1.readObject();
            }

            this._updates = (Collection)var1.readObject();
            this._deletes = (Collection)var1.readObject();
            this._sourceId = var1.readUTF();
            this._senderIp = var1.readUTF();
        } catch (ClassNotFoundException var3) {
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "("
                + "payload=" + + _payload
                + ", addClasses=" + serialize(_addClasses)
                + ", addIds=" + serialize(_addIds)
                + ", updates=" + serialize(_updates)
                + ", deletes=" + serialize(_deletes)
                + ", sourceId=" + _sourceId
                + ", senderIp=" + _senderIp
                + ")";
    }

    private String serialize(Collection col) {
        if (col == null) {
            return "(empty collection)";
        }
        return (String) col.stream().map(c -> "" + c).collect(Collectors.joining(","));
    }
}