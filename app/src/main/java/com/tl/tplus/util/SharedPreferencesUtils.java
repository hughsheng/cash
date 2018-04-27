package com.tl.tplus.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by fangpenglin on 16/6/10.
 * android自带的数据存储类
 */
public class SharedPreferencesUtils {

  public static final String SP_NAME = "ft_theme_sp";
  private static SharedPreferencesUtils instance;
  private final Context context;

  private SharedPreferencesUtils(Context context) {
    this.context = context;
  }

  public static SharedPreferencesUtils getInstance(Context context) {
    if (instance == null) {
      synchronized (SharedPreferencesUtils.class) {
        if (instance == null) {
          instance = new SharedPreferencesUtils(context);
        }
      }
    }
    return instance;
  }

  @SuppressWarnings("unused")
  public static void removeData(Context context, String key) {   //移除sp中的指定元素
    SharedPreferences sp = PreferenceManager
        .getDefaultSharedPreferences(context.getApplicationContext());
    sp.edit().remove(key).apply();
  }

  //自定义spname
  @SuppressWarnings("unused")
  public void saveData(String spname, String key, Object object) {
    SharedPreferences sp = context.getApplicationContext().getSharedPreferences(spname, Context.MODE_PRIVATE);
    saveEditData(sp, key, object);
  }

  private void saveEditData(SharedPreferences sp, String key, Object object) {
    SharedPreferences.Editor editor = sp.edit();
    if (object instanceof String) {
      editor.putString(key, (String) object);
    } else if (object instanceof Integer) {
      editor.putInt(key, (Integer) object);
    } else if (object instanceof Boolean) {
      editor.putBoolean(key, (Boolean) object);
    } else if (object instanceof Float) {
      editor.putFloat(key, (Float) object);
    } else if (object instanceof Long) {
      editor.putLong(key, (Long) object);
    }
    editor.apply();
  }

  public Object getData(String spname, String key, Object defaultObject) {
    SharedPreferences sp = context.getApplicationContext().getSharedPreferences(spname, Context.MODE_PRIVATE);
    return getEditData(sp, key, defaultObject);
  }

  private Object getEditData(SharedPreferences sp, String key, Object defaultObject) {
    if (defaultObject instanceof String) {
      return sp.getString(key, (String) defaultObject);
    } else if (defaultObject instanceof Integer) {
      return sp.getInt(key, (Integer) defaultObject);
    } else if (defaultObject instanceof Boolean) {
      return sp.getBoolean(key, (Boolean) defaultObject);
    } else if (defaultObject instanceof Float) {
      return sp.getFloat(key, (Float) defaultObject);
    } else if (defaultObject instanceof Long) {
      return sp.getLong(key, (Long) defaultObject);
    } else {
      throw new RuntimeException("只支持基本类型");
    }
  }


  //自定义spname
  @SuppressWarnings("unused")
  public void removeListData(String spname, String key) {
    SharedPreferences sp = context.getApplicationContext().getSharedPreferences(spname, Context.MODE_PRIVATE);
    sp.edit().remove(key).apply();

  }

  //自定义spname
  @SuppressWarnings("unused")
  public void saveListData(String spname, String key, List<Map<String, String>> dataList) {
    SharedPreferences sp = context.getApplicationContext().getSharedPreferences(spname, Context.MODE_PRIVATE);
    saveEditListData(sp, key, dataList);

  }

  public void saveStringListData(String spname, String key, List<String> ids) {
    SharedPreferences sp = context.getApplicationContext().getSharedPreferences(spname, Context.MODE_PRIVATE);
    Gson gson = new Gson();
    //转换成json数据，再保存
    String mJsonArray = gson.toJson(ids);
    SharedPreferences.Editor editor = sp.edit();
    editor.putString(key, mJsonArray.toString());
    editor.apply();

  }

  public List<String> getStringListData(String spname, String key){
    SharedPreferences sp = context.getApplicationContext().getSharedPreferences(spname, Context.MODE_PRIVATE);
    String result = sp.getString(key, null);
    List<String> ids = new ArrayList<>();
    if (null == result) {
      return ids;
    }
    Gson gson = new Gson();
    ids = gson.fromJson(result, new TypeToken<List<String>>() {
    }.getType());
    return ids;
  }

  //自定义spname
  @SuppressWarnings("unused")
  public void saveMapData(String spname, String key, Map<String, String> mapData) {
    SharedPreferences sp = context.getApplicationContext().getSharedPreferences(spname, Context.MODE_PRIVATE);
    saveEditMapData(sp, key, mapData);

  }
	public Map<String,String> getMapData(String spname, String key){
		SharedPreferences sp = context.getApplicationContext().getSharedPreferences(spname, Context.MODE_PRIVATE);
		return getEditMapData(sp,key);
	}

  //自定义spname
  @SuppressWarnings("unused")
  public List<Map<String, String>> getListData(String spname, String key) {
    SharedPreferences sp = context.getApplicationContext().getSharedPreferences(spname, Context.MODE_PRIVATE);
    return getEditListData(sp, key);
  }

  private void saveEditListData(SharedPreferences sp, String key, List<Map<String, String>> dataList) {
    JSONArray mJsonArray = new JSONArray();
    for (int i = 0; i < dataList.size(); i++) {
      Map<String, String> itemMap = dataList.get(i);
      Iterator<Map.Entry<String, String>> iterator = itemMap.entrySet().iterator();

      JSONObject object = new JSONObject();

      while (iterator.hasNext()) {
        Map.Entry<String, String> entry = iterator.next();
        try {
          object.put(entry.getKey(), entry.getValue());
        } catch (JSONException e) {

        }
      }
      mJsonArray.put(object);
    }

    SharedPreferences.Editor editor = sp.edit();
    editor.putString(key, mJsonArray.toString());
    editor.apply();
  }

  private void saveEditMapData(SharedPreferences sp, String key, Map<String, String> mapData) {

    Iterator<Map.Entry<String, String>> iterator = mapData.entrySet().iterator();

    JSONObject object = new JSONObject();

    while (iterator.hasNext()) {
      Map.Entry<String, String> entry = iterator.next();
      try {
        object.put(entry.getKey(), entry.getValue());
      } catch (JSONException e) {

      }
    }
    SharedPreferences.Editor editor = sp.edit();
    editor.putString(key, object.toString());
    editor.apply();
  }

  private Map<String,String> getEditMapData(SharedPreferences sp, String key){
    Map<String, String> mapData = new HashMap<>();
    String result = sp.getString(key, "{\"nullKey\": \"nullVelue\"}");
	  Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
	  Map<String, String> map = gson.fromJson(result, new TypeToken<Map<String, String>>() {}.getType());
	  Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
	  while (iterator.hasNext()) {
		  Map.Entry<String, String> entry = iterator.next();
		  mapData.put(entry.getKey(), entry.getValue());
	  }
    return mapData;
  }

  private List<Map<String, String>> getEditListData(SharedPreferences sp, String key) {
    List<Map<String, String>> datas = new ArrayList<Map<String, String>>();
    String result = sp.getString(key, "");
    try {
      JSONArray array = new JSONArray(result);
      for (int i = 0; i < array.length(); i++) {
        JSONObject itemObject = array.getJSONObject(i);
        Map<String, String> itemMap = new HashMap<String, String>();
        JSONArray names = itemObject.names();
        if (names != null) {
          for (int j = 0; j < names.length(); j++) {
            String name = names.getString(j);
            String value = itemObject.getString(name);
            itemMap.put(name, value);
          }
        }
        datas.add(itemMap);
      }
    } catch (JSONException e) {

    }
    return datas;
  }

}
