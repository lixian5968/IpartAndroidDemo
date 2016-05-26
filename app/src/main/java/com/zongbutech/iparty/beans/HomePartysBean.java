package com.zongbutech.iparty.beans;


import com.loveiparty.http.db.Party;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by lixian on 2016/3/10.
 */
public class HomePartysBean implements Serializable {
    public int code;
    public List<Party> data;
    public Date last_update_time;




}

