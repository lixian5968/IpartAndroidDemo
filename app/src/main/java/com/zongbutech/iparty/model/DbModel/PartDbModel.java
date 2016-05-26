package com.zongbutech.iparty.model.DbModel;

import android.content.Context;
import android.util.Log;

import com.loveiparty.http.Bean.PhotoBean;
import com.loveiparty.http.db.Party;
import com.loveiparty.http.db.PartyDao;
import com.loveiparty.http.db.UrlString;
import com.loveiparty.http.db.UrlStringDao;
import com.zongbutech.iparty.beans.HomePartyBean;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by lixian on 2016/5/25.
 */
public class PartDbModel extends BaseDbModel {
    PartyDao mPartyDao;
    UrlStringDao mUrlStringDao;

    public PartDbModel(Context ct) {
        super(ct);
        mPartyDao = daoSession.getPartyDao();
        mUrlStringDao = daoSession.getUrlStringDao();
    }


    public List<Party> getParts(int type, int page) {
        try {
            QueryBuilder<Party> builder = mPartyDao.queryBuilder();
            getAll(builder);
            if(type!=0){
                builder.where(PartyDao.Properties.Types.like("%" + type + "%"));
            }
            builder.orderDesc(PartyDao.Properties.Update_time);
            builder.limit(10);
            builder.offset(page * 10);
            List<Party> Beans = builder.list();
            return Beans;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void getAll(QueryBuilder<Party> builder) {
        List<Party> Beans = builder.list();
        Log.e("lx","");
    }

    public void saveDbParty(HomePartyBean mHomePartyBean) {
        Party mParty = getPartyById((long) mHomePartyBean.party_id);
        if(mParty==null){
            mParty = new Party();
            mParty.setId((long) mHomePartyBean.party_id);
            insertParty(mHomePartyBean, mParty);
            mPartyDao.insert(mParty);
        }else{
            insertParty(mHomePartyBean, mParty);
            mPartyDao.update(mParty);
        }
        mPartyDao.insertOrReplace(mParty);

        for (PhotoBean mPhotoBean : mHomePartyBean.photos) {
            UrlString mUrlString = getUrlStringByUrl(mPhotoBean.url);
            if(mUrlString==null){
                mUrlString = new UrlString();
                mUrlString.setUrl(mPhotoBean.url);
                mUrlString.setUrlType("PartyPhoto");
                mUrlStringDao.insert(mUrlString);
            }else{
                mUrlString.setUrl(mPhotoBean.url);
                mUrlString.setUrlType("PartyPhoto");
                mUrlStringDao.update(mUrlString);
            }
        }
    }

    //DbParty 插入数据
    private void insertParty(HomePartyBean mHomePartyBean, Party mParty) {
        mParty.setAddress_text(mHomePartyBean.address_text);
        mParty.setAddress_thumbnail(mHomePartyBean.address_thumbnail);
        mParty.setAddress_url(mHomePartyBean.address_url);
        mParty.setDeadline_time(mHomePartyBean.deadline_time);
        mParty.setEnd_time(mHomePartyBean.end_time);
        mParty.setFavorite_num(mHomePartyBean.favorite_num);
        mParty.setHead_photo(mHomePartyBean.head_photo);
        mParty.setUpdate_time(mHomePartyBean.last_update_time);
        mParty.setLeastnum(mHomePartyBean.least_num);
        mParty.setLimited(mHomePartyBean.limited);
        mParty.setMaximum_num(mHomePartyBean.maximum_num);
        mParty.setOld_price_man(mHomePartyBean.old_price_man);
        mParty.setOld_price_woman(mHomePartyBean.old_price_woman);
        mParty.setPic_base_url(mHomePartyBean.pic_base_url);
        mParty.setPrice_man(mHomePartyBean.price_man);
        mParty.setPrice_woman(mHomePartyBean.price_woman);
        mParty.setPublisher_avatar(mHomePartyBean.publisher_avatar);
        mParty.setPublisher_id(mHomePartyBean.publisher_id);
        mParty.setPublisher_name(mHomePartyBean.publisher_name);
        mParty.setPublisher_star(mHomePartyBean.publisher_star);
        mParty.setRecommend(mHomePartyBean.recommend);
        mParty.setSold(mHomePartyBean.sold_num);
        mParty.setStart_time(mHomePartyBean.start_time);
        mParty.setTime_text(mHomePartyBean.time_text);
        mParty.setTime_type(mHomePartyBean.time_type);
        mParty.setTitle(mHomePartyBean.title);
        mParty.setTypes(mHomePartyBean.types);
        mParty.setWeek_activity(mHomePartyBean.week_activity);
    }

    private Party getPartyById(Long id) {
        try {
            QueryBuilder<Party> builder = mPartyDao.queryBuilder();
            builder.where(PartyDao.Properties.Id.eq(id));
            List<Party> Beans = builder.list();
            return Beans.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private UrlString getUrlStringByUrl(String url) {
        try {
            QueryBuilder<UrlString> builder = mUrlStringDao.queryBuilder();
            builder.where(UrlStringDao.Properties.Url.eq(url));
            List<UrlString> Beans = builder.list();
            return Beans.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
