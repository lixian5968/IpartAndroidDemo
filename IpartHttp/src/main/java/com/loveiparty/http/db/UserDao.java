package com.loveiparty.http.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.loveiparty.http.db.User;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER".
*/
public class UserDao extends AbstractDao<User, Long> {

    public static final String TABLENAME = "USER";

    /**
     * Properties of entity User.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Introduction = new Property(0, String.class, "introduction", false, "INTRODUCTION");
        public final static Property Talent_avatar = new Property(1, String.class, "talent_avatar", false, "TALENT_AVATAR");
        public final static Property Talent_details = new Property(2, String.class, "talent_details", false, "TALENT_DETAILS");
        public final static Property Talent_name = new Property(3, String.class, "talent_name", false, "TALENT_NAME");
        public final static Property Talent_phone = new Property(4, String.class, "talent_phone", false, "TALENT_PHONE");
        public final static Property Talent_bg_img = new Property(5, String.class, "talent_bg_img", false, "TALENT_BG_IMG");
        public final static Property Talent_stars = new Property(6, Double.class, "talent_stars", false, "TALENT_STARS");
        public final static Property Talent_types = new Property(7, Integer.class, "talent_types", false, "TALENT_TYPES");
        public final static Property User_id = new Property(8, Integer.class, "user_id", false, "USER_ID");
        public final static Property Id = new Property(9, Long.class, "id", true, "_id");
        public final static Property Avatar = new Property(10, String.class, "avatar", false, "AVATAR");
        public final static Property Has_change_sex = new Property(11, Integer.class, "has_change_sex", false, "HAS_CHANGE_SEX");
        public final static Property Last_update_time = new Property(12, java.util.Date.class, "last_update_time", false, "LAST_UPDATE_TIME");
        public final static Property Nickname = new Property(13, String.class, "nickname", false, "NICKNAME");
        public final static Property Open_id = new Property(14, String.class, "open_id", false, "OPEN_ID");
        public final static Property Register_time = new Property(15, java.util.Date.class, "register_time", false, "REGISTER_TIME");
        public final static Property Sex = new Property(16, Integer.class, "sex", false, "SEX");
        public final static Property Telephone = new Property(17, String.class, "telephone", false, "TELEPHONE");
        public final static Property User_type = new Property(18, Integer.class, "user_type", false, "USER_TYPE");
        public final static Property ObjectId = new Property(19, String.class, "objectId", false, "OBJECT_ID");
        public final static Property Province = new Property(20, String.class, "province", false, "PROVINCE");
        public final static Property City = new Property(21, String.class, "city", false, "CITY");
        public final static Property Country = new Property(22, String.class, "country", false, "COUNTRY");
        public final static Property Access_token = new Property(23, String.class, "access_token", false, "ACCESS_TOKEN");
        public final static Property Platform_id = new Property(24, Integer.class, "platform_id", false, "PLATFORM_ID");
        public final static Property Thirdparty_account = new Property(25, String.class, "thirdparty_account", false, "THIRDPARTY_ACCOUNT");
    };


    public UserDao(DaoConfig config) {
        super(config);
    }
    
    public UserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER\" (" + //
                "\"INTRODUCTION\" TEXT," + // 0: introduction
                "\"TALENT_AVATAR\" TEXT," + // 1: talent_avatar
                "\"TALENT_DETAILS\" TEXT," + // 2: talent_details
                "\"TALENT_NAME\" TEXT," + // 3: talent_name
                "\"TALENT_PHONE\" TEXT," + // 4: talent_phone
                "\"TALENT_BG_IMG\" TEXT," + // 5: talent_bg_img
                "\"TALENT_STARS\" REAL," + // 6: talent_stars
                "\"TALENT_TYPES\" INTEGER," + // 7: talent_types
                "\"USER_ID\" INTEGER," + // 8: user_id
                "\"_id\" INTEGER PRIMARY KEY ," + // 9: id
                "\"AVATAR\" TEXT," + // 10: avatar
                "\"HAS_CHANGE_SEX\" INTEGER," + // 11: has_change_sex
                "\"LAST_UPDATE_TIME\" INTEGER," + // 12: last_update_time
                "\"NICKNAME\" TEXT," + // 13: nickname
                "\"OPEN_ID\" TEXT," + // 14: open_id
                "\"REGISTER_TIME\" INTEGER," + // 15: register_time
                "\"SEX\" INTEGER," + // 16: sex
                "\"TELEPHONE\" TEXT," + // 17: telephone
                "\"USER_TYPE\" INTEGER," + // 18: user_type
                "\"OBJECT_ID\" TEXT," + // 19: objectId
                "\"PROVINCE\" TEXT," + // 20: province
                "\"CITY\" TEXT," + // 21: city
                "\"COUNTRY\" TEXT," + // 22: country
                "\"ACCESS_TOKEN\" TEXT," + // 23: access_token
                "\"PLATFORM_ID\" INTEGER," + // 24: platform_id
                "\"THIRDPARTY_ACCOUNT\" TEXT);"); // 25: thirdparty_account
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, User entity) {
        stmt.clearBindings();
 
        String introduction = entity.getIntroduction();
        if (introduction != null) {
            stmt.bindString(1, introduction);
        }
 
        String talent_avatar = entity.getTalent_avatar();
        if (talent_avatar != null) {
            stmt.bindString(2, talent_avatar);
        }
 
        String talent_details = entity.getTalent_details();
        if (talent_details != null) {
            stmt.bindString(3, talent_details);
        }
 
        String talent_name = entity.getTalent_name();
        if (talent_name != null) {
            stmt.bindString(4, talent_name);
        }
 
        String talent_phone = entity.getTalent_phone();
        if (talent_phone != null) {
            stmt.bindString(5, talent_phone);
        }
 
        String talent_bg_img = entity.getTalent_bg_img();
        if (talent_bg_img != null) {
            stmt.bindString(6, talent_bg_img);
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
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(10, id);
        }
 
        String avatar = entity.getAvatar();
        if (avatar != null) {
            stmt.bindString(11, avatar);
        }
 
        Integer has_change_sex = entity.getHas_change_sex();
        if (has_change_sex != null) {
            stmt.bindLong(12, has_change_sex);
        }
 
        java.util.Date last_update_time = entity.getLast_update_time();
        if (last_update_time != null) {
            stmt.bindLong(13, last_update_time.getTime());
        }
 
        String nickname = entity.getNickname();
        if (nickname != null) {
            stmt.bindString(14, nickname);
        }
 
        String open_id = entity.getOpen_id();
        if (open_id != null) {
            stmt.bindString(15, open_id);
        }
 
        java.util.Date register_time = entity.getRegister_time();
        if (register_time != null) {
            stmt.bindLong(16, register_time.getTime());
        }
 
        Integer sex = entity.getSex();
        if (sex != null) {
            stmt.bindLong(17, sex);
        }
 
        String telephone = entity.getTelephone();
        if (telephone != null) {
            stmt.bindString(18, telephone);
        }
 
        Integer user_type = entity.getUser_type();
        if (user_type != null) {
            stmt.bindLong(19, user_type);
        }
 
        String objectId = entity.getObjectId();
        if (objectId != null) {
            stmt.bindString(20, objectId);
        }
 
        String province = entity.getProvince();
        if (province != null) {
            stmt.bindString(21, province);
        }
 
        String city = entity.getCity();
        if (city != null) {
            stmt.bindString(22, city);
        }
 
        String country = entity.getCountry();
        if (country != null) {
            stmt.bindString(23, country);
        }
 
        String access_token = entity.getAccess_token();
        if (access_token != null) {
            stmt.bindString(24, access_token);
        }
 
        Integer platform_id = entity.getPlatform_id();
        if (platform_id != null) {
            stmt.bindLong(25, platform_id);
        }
 
        String thirdparty_account = entity.getThirdparty_account();
        if (thirdparty_account != null) {
            stmt.bindString(26, thirdparty_account);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 9) ? null : cursor.getLong(offset + 9);
    }    

    /** @inheritdoc */
    @Override
    public User readEntity(Cursor cursor, int offset) {
        User entity = new User( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // introduction
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // talent_avatar
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // talent_details
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // talent_name
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // talent_phone
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // talent_bg_img
            cursor.isNull(offset + 6) ? null : cursor.getDouble(offset + 6), // talent_stars
            cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7), // talent_types
            cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8), // user_id
            cursor.isNull(offset + 9) ? null : cursor.getLong(offset + 9), // id
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // avatar
            cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11), // has_change_sex
            cursor.isNull(offset + 12) ? null : new java.util.Date(cursor.getLong(offset + 12)), // last_update_time
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // nickname
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // open_id
            cursor.isNull(offset + 15) ? null : new java.util.Date(cursor.getLong(offset + 15)), // register_time
            cursor.isNull(offset + 16) ? null : cursor.getInt(offset + 16), // sex
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // telephone
            cursor.isNull(offset + 18) ? null : cursor.getInt(offset + 18), // user_type
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // objectId
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // province
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // city
            cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22), // country
            cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23), // access_token
            cursor.isNull(offset + 24) ? null : cursor.getInt(offset + 24), // platform_id
            cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25) // thirdparty_account
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, User entity, int offset) {
        entity.setIntroduction(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setTalent_avatar(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setTalent_details(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTalent_name(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setTalent_phone(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setTalent_bg_img(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setTalent_stars(cursor.isNull(offset + 6) ? null : cursor.getDouble(offset + 6));
        entity.setTalent_types(cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7));
        entity.setUser_id(cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8));
        entity.setId(cursor.isNull(offset + 9) ? null : cursor.getLong(offset + 9));
        entity.setAvatar(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setHas_change_sex(cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11));
        entity.setLast_update_time(cursor.isNull(offset + 12) ? null : new java.util.Date(cursor.getLong(offset + 12)));
        entity.setNickname(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setOpen_id(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setRegister_time(cursor.isNull(offset + 15) ? null : new java.util.Date(cursor.getLong(offset + 15)));
        entity.setSex(cursor.isNull(offset + 16) ? null : cursor.getInt(offset + 16));
        entity.setTelephone(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setUser_type(cursor.isNull(offset + 18) ? null : cursor.getInt(offset + 18));
        entity.setObjectId(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setProvince(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setCity(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setCountry(cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22));
        entity.setAccess_token(cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23));
        entity.setPlatform_id(cursor.isNull(offset + 24) ? null : cursor.getInt(offset + 24));
        entity.setThirdparty_account(cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(User entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(User entity) {
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
