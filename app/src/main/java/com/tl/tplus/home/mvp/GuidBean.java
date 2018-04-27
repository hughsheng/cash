package com.tl.tplus.home.mvp;

import com.tl.tplus.base.api.bean.BaseResultBean;

/**
 * Created by sztangli on 2018-3-13.
 */

public class GuidBean extends BaseResultBean {

  /**
   * data : {"guid":"d4f86c2f7aae96c3e5e80a1a76ec6b23"}
   */

  private DataBean data;

  public DataBean getData() {
    return data;
  }

  public void setData(DataBean data) {
    this.data = data;
  }

  public static class DataBean {
    /**
     * guid : d4f86c2f7aae96c3e5e80a1a76ec6b23
     */

    private String guid;

    public String getGuid() {
      return guid;
    }

    public void setGuid(String guid) {
      this.guid = guid;
    }
  }
}
