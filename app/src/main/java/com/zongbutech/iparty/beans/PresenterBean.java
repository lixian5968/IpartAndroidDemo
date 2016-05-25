package com.zongbutech.iparty.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lixian on 2016/5/25.
 */
public class PresenterBean implements Serializable {
    public List OldList;
    public int IntType;
    public String StringType;
    public int page;
    public List NewList;

    public String error;
}
