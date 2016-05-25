package com.zongbutech.iparty.utils.partBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lixian on 2016/3/10.
 */
public class ClientMaps {

    public static Map<String,Integer> partTypeToName = new HashMap<>();
    static {
        partTypeToName.put("全部",0);
        partTypeToName.put("夜生活",1);
        partTypeToName.put("派对",2);
        partTypeToName.put("特色体验",3);
        partTypeToName.put("电影",4);
        partTypeToName.put("运动",5);
        partTypeToName.put("美食",6);
        partTypeToName.put("音乐",7);
        partTypeToName.put("旅游",8);
        partTypeToName.put("高端生活",9);
        partTypeToName.put("其它",10);
    }


}
