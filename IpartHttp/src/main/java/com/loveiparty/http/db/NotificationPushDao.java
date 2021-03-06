package com.loveiparty.http.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.loveiparty.http.db.NotificationPush;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "NOTIFICATION_PUSH".
*/
public class NotificationPushDao extends AbstractDao<NotificationPush, Long> {

    public static final String TABLENAME = "NOTIFICATION_PUSH";

    /**
     * Properties of entity NotificationPush.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ObjectId = new Property(1, String.class, "objectId", false, "OBJECT_ID");
        public final static Property User_id = new Property(2, String.class, "user_id", false, "USER_ID");
        public final static Property Content = new Property(3, String.class, "content", false, "CONTENT");
        public final static Property Send_state = new Property(4, String.class, "send_state", false, "SEND_STATE");
        public final static Property Is_read = new Property(5, Boolean.class, "is_read", false, "IS_READ");
        public final static Property Extras = new Property(6, String.class, "extras", false, "EXTRAS");
        public final static Property Created_time = new Property(7, java.util.Date.class, "created_time", false, "CREATED_TIME");
        public final static Property Updated_time = new Property(8, java.util.Date.class, "updated_time", false, "UPDATED_TIME");
    };


    public NotificationPushDao(DaoConfig config) {
        super(config);
    }
    
    public NotificationPushDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"NOTIFICATION_PUSH\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"OBJECT_ID\" TEXT," + // 1: objectId
                "\"USER_ID\" TEXT," + // 2: user_id
                "\"CONTENT\" TEXT," + // 3: content
                "\"SEND_STATE\" TEXT," + // 4: send_state
                "\"IS_READ\" INTEGER," + // 5: is_read
                "\"EXTRAS\" TEXT," + // 6: extras
                "\"CREATED_TIME\" INTEGER," + // 7: created_time
                "\"UPDATED_TIME\" INTEGER);"); // 8: updated_time
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"NOTIFICATION_PUSH\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, NotificationPush entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String objectId = entity.getObjectId();
        if (objectId != null) {
            stmt.bindString(2, objectId);
        }
 
        String user_id = entity.getUser_id();
        if (user_id != null) {
            stmt.bindString(3, user_id);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(4, content);
        }
 
        String send_state = entity.getSend_state();
        if (send_state != null) {
            stmt.bindString(5, send_state);
        }
 
        Boolean is_read = entity.getIs_read();
        if (is_read != null) {
            stmt.bindLong(6, is_read ? 1L: 0L);
        }
 
        String extras = entity.getExtras();
        if (extras != null) {
            stmt.bindString(7, extras);
        }
 
        java.util.Date created_time = entity.getCreated_time();
        if (created_time != null) {
            stmt.bindLong(8, created_time.getTime());
        }
 
        java.util.Date updated_time = entity.getUpdated_time();
        if (updated_time != null) {
            stmt.bindLong(9, updated_time.getTime());
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public NotificationPush readEntity(Cursor cursor, int offset) {
        NotificationPush entity = new NotificationPush( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // objectId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // user_id
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // content
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // send_state
            cursor.isNull(offset + 5) ? null : cursor.getShort(offset + 5) != 0, // is_read
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // extras
            cursor.isNull(offset + 7) ? null : new java.util.Date(cursor.getLong(offset + 7)), // created_time
            cursor.isNull(offset + 8) ? null : new java.util.Date(cursor.getLong(offset + 8)) // updated_time
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, NotificationPush entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setObjectId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setUser_id(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setContent(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setSend_state(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setIs_read(cursor.isNull(offset + 5) ? null : cursor.getShort(offset + 5) != 0);
        entity.setExtras(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setCreated_time(cursor.isNull(offset + 7) ? null : new java.util.Date(cursor.getLong(offset + 7)));
        entity.setUpdated_time(cursor.isNull(offset + 8) ? null : new java.util.Date(cursor.getLong(offset + 8)));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(NotificationPush entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(NotificationPush entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
