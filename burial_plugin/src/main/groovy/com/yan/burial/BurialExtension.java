package com.yan.burial;

import java.util.ArrayList;
import java.util.List;

public class BurialExtension {

  public List<String> foreList = new ArrayList<>();
  public List<String> ignoreList = new ArrayList<>();
  public boolean logEnable = false;
  public boolean listenerWithMethodDetail = true;
  // 新增参数：控制是否只插桩项目代码，默认为true
  public boolean instrumentOnlyProjectCode = true;

  @Override public String toString() {
    return "BurialExtension{" +
        ", whitelist=" + foreList +
        ", blacklist=" + ignoreList +
        ", logEnable=" + logEnable +
        ", listenerWithMethodDetail=" + listenerWithMethodDetail +
        ", instrumentOnlyProjectCode=" + instrumentOnlyProjectCode +
        '}';
  }

  boolean isInWhitelist(String fullQualifiedClassName) {
    boolean inWhiteList = false;
    for (String item : foreList) {
      if (fullQualifiedClassName.contains(item)) {
        inWhiteList = true;
        break;
      }
    }
    return inWhiteList;
  }

  boolean isInBlacklist(String fullQualifiedClassName) {
    boolean inBlacklist = false;
    for (String item : ignoreList) {
      if (fullQualifiedClassName.contains(item)) {
        inBlacklist = true;
        break;
      }
    }
    return inBlacklist;
  }

  static final String PLUGIN_LIBRARY = "com.yan.burial.method.timer";
}
