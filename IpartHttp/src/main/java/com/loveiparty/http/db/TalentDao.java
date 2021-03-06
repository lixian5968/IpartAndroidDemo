package com.loveiparty.http.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.loveiparty.http.db.Talent;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "TALENT".
*/
public class TalentDao extends AbstractDao<Talent, Long> {

    public static final String TABLENAME = "TALENT";

    /**
     * Properties of entity Talent.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Introduction = new Property(1, String.class, "introduction", false, "INTRODUCTION");
        public final static Property Talent_avatar = new Property(2, String.class, "talent_avatar", false, "TALENT_AVATAR");
        public final static Property Talent_details = new Property(3, String.class, "talent_details", false, "TALENT_DETAILS");
        public final static Property Talent_name = new Property(4, String.class, "talent_name", false, "TALENT_NAME");
        public final static Property Talent_phone = new Property(5, String.class, "talent_phone", false, "TALENT_PHONE");
        public final static Property Talent_stars = new Property(6, Double.class, "talent_stars", false, "TALENT_STARS");
        public final static Property Talent_types = new Property(7, Integer.class, "talent_types", false, "TALENT_TYPES");
        public final static Property User_id = new Property(8, Integer.class, "user_id", false, "USER_ID");
        public final static Property ObjectId = new Property(9, String.class, "objectId", false, "OBJECT_ID");
        public final static Property Real_name = new Property(10, String.class, "real_name", false, "REAL_NAME");
        public final static Property Avatar = new Property(11, String.class, "avatar", false, "AVATAR");
        public final static Property Identify = new Property(12, Integer.class, "identify", false, "IDENTIFY");
        public final static Property City = new Property(13, String.class, "city", false, "CITY");
        public final static Property Province = new Property(14, String.class, "province", false, "PROVINCE");
        public final static Property Wechat_account = new Property(15, String.class, "wechat_account", false, "WECHAT_ACCOUNT");
        public final static Property Phone = new Property(16, String.class, "phone", false, "PHONE");
        public final static Property Photo_ids = new Property(17, String.class, "photo_ids", false, "PHOTO_IDS");
        public final static Property Details = new Property(18, String.class, "details", false, "DETAILS");
        public final static Property Type_ids = new Property(19, Integer.class, "type_ids", false, "TYPE_IDS");
        public final static Property Created_time = new Property(20, java.util.Date.class, "created_time", false, "CREATED_TIME");
        public final static Property Is_audited = new Property(21, Integer.class, "is_audited", false, "IS_AUDITED");
        public final static Property Update_time = new Property(22, java.util.Date.class, "update_time", false, "UPDATE_TIME");
    };


    public TalentDao(DaoConfig config) {
        super(config);
    }
    
    public TalentDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"TALENT\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"INTRODUCTION\" TEXT," + // 1: introduction
                "\"TALENT_AVATAR\" TEXT," + // 2: talent_avatar
                "\"TALENT_DETAILS\" TEXT," + // 3: talent_details
                "\"TALENT_NAME\" TEXT," + // 4: talent_name
                "\"TALENT_PHONE\" TEXT," + // 5: talent_phone
                "\"TALENT_STARS\" REAL," + // 6: talent_stars
                "\"TALENT_TYPES\" INTEGER," + // 7: talent_types
                "\"USER_ID\" INTEGER," + // 8: user_id
                "\"OBJECT_ID\" TEXT," + // 9: objectId
                "\"REAL_NAME\" TEXT," + // 10: real_name
                "\"AVATAR\" TEXT," + // 11: avatar
                "\"IDENTIFY\" INTEGER," + // 12: identify
                "\"CITY\" TEXT," + // 13: city
                "\"PROVINCE\" TEXT," + // 14: province
                "\"WECHAT_ACCOUNT\" TEXT," + // 15: wechat_account
                "\"PHONE\" TEXT," + // 16: phone
                "\"PHOTO_IDS\" TEXT," + // 17: photo_ids
                "\"DETAILS\" TEXT," + // 18: details
                "\"TYPE_IDS\" INTEGER," + // 19: type_ids
                "\"CREATED_TIME\" INTEGER," + // 20: created_time
                "\"IS_AUDITED\" INTEGER," + // 21: is_audited
                "\"UPDATE_TIME\" INTEGER);"); // 22: update_time
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TALENT\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Talent entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String introduction = entity.getIntroduction();
        if (introduction != null) {
            stmt.bindString(2, introduction);
        }
 
        String talent_avatar = entity.getTalent_avatar();
        if (talent_avatar != null) {
            stmt.bindString(3, talent_avatar);
        }
 
        String talent_details = entity.getTalent_details();
        if (talent_details != null) {
            stmt.bindString(4, talent_details);
        }
 
        String talent_name = entity.getTalent_name();
        if (talent_name != null) {
            stmt.bindString(5, talent_name);
        }
 
        String talent_phone = entity.getTalent_phone();
        if (talent_phone != null) {
            stmt.bindString(6, talent_phone);
        }
 
        Double talent_stars = entity.getTalent_stars();
        if (talent_stars != null) {
            stmt.bindDouble(7, talent_stars);
        }
 
        Integer talent_types = entity.getTalent_types();
        if (talent_types != null) {
            stmt.bindLong(8, talent_types);
        }
 
        Integer user_id = entity.getUser_id();
        if (user_id != null) {
            stmt.bindLong(9, user_id);
        }
 
        String objectId = entity.getObjectId();
        if (objectId != null) {
            stmt.bindString(10, objectId);
        }
 
        String real_name = entity.getReal_name();
        if (real_name != null) {
            stmt.bindString(11, real_name);
        }
 
        String avatar = entity.getAvatar();
        if (avatar != null) {
            stmt.bindString(12, avatar);
        }
 
        Integer identify = entity.getIdentify();
        if (identify != null) {
            stmt.bindLong(13, identify);
        }
 
        String city = entity.getCity();
        if (city != null) {
            stmt.bindString(14, city);
        }
 
        String province = entity.getProvince();
        if (province != null) {
            stmt.bindString(15, province);
        }
 
        String wechat_account = entity.getWechat_account();
        if (wechat_account != null) {
            stmt.bindString(16, wechat_account);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(17, phone);
        }
 
        String photo_ids = entity.getPhoto_ids();
        if (photo_ids != null) {
            stmt.bindString(18, photo_ids);
        }
 
        String details = entity.getDetails();
        if (details != null) {
            stmt.bindString(19, details);
        }
 
        Integer type_ids = entity.getType_ids();
        if (type_ids != null) {
            stmt.bindLong(20, type_ids);
        }
 
        java.util.Date created_time = entity.getCreated_time();
        if (created_time != null) {
            stmt.bindLong(21, created_time.getTime());
        }
 
        Integer is_audited = entity.getIs_audited();
        if (is_audited != null) {
            stmt.bindLong(22, is_audited);
        }
 
        java.util.Date update_time = entity.getUpdate_time();
        if (update_time != null) {
            stmt.bindLong(23, update_time.getTime());
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Talent readEntity(Cursor cursor, int offset) {
        Talent entity = new Talent( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // introduction
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // talent_avatar
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // talent_details
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // talent_name
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // talent_phone
            cursor.isNull(offset + 6) ? null : cursor.getDouble(offset + 6), // talent_stars
            cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7), // talent_types
            cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8), // user_id
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // objectId
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // real_name
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // avatar
            cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12), // identify
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // city
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // province
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // wechat_account
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // phone
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // photo_ids
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // details
            cursor.isNull(offset + 19) ? null : cursor.getInt(offset + 19), // type_ids
            cursor.isNull(offset + 20) ? null : new java.util.Date(cursor.getLong(offset + 20)), // created_time
            cursor.isNull(offset + 21) ? null : cursor.getInt(offset + 21), // is_audited
            cursor.isNull(offset + 22) ? null : new java.util.Date(cursor.getLong(offset + 22)) // update_time
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Talent entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setIntroduction(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setTalent_avatar(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTalent_details(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setTalent_name(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setTalent_phone(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setTalent_stars(cursor.isNull(offset + 6) ? null : cursor.getDouble(offset + 6));
        entity.setTalent_types(cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7));
        entity.setUser_id(cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8));
        entity.setObjectId(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setReal_name(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setAvatar(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setIdentify(cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12));
        entity.setCity(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setProvince(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setWechat_account(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setPhone(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setPhoto_ids(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setDetails(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setType_ids(cursor.isNull(offset + 19) ? null : cursor.getInt(offset + 19));
        entity.setCreated_time(cursor.isNull(offset + 20) ? null : new java.util.Date(cursor.getLong(offset + 20)));
        entity.setIs_audited(cursor.isNull(offset + 21) ? null : cursor.getInt(offset + 21));
        entity.setUpdate_time(cursor.isNull(offset + 22) ? null : new java.util.Date(cursor.getLong(offset + 22)));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Talent entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Talent entity) {
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
