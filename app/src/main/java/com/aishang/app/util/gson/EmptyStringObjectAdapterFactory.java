package com.aishang.app.util.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by song on 2016/3/7.
 */
public class EmptyStringObjectAdapterFactory implements TypeAdapterFactory {

  private static final String TAG = "GsonAdapterFactory";

  @SuppressWarnings("unchecked") public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {

    //Log.i(TAG, "create: ------------------1"
    //    + "  "
    //    + type.getRawType().getSimpleName()
    //    + "   "
    //    + type.getRawType());

    //if (type.getRawType().isAssignableFrom(EmptyObject.class)) {
    return (TypeAdapter<T>) new Adapter(gson);
    // }
    //Log.i(TAG, "create: ------------------2");
    //return null;
  }

  private static final class Adapter extends TypeAdapter<Object> {

    private final Gson gson;

    private Adapter(Gson gson) {
      this.gson = gson;
    }

    @Override public Object read(JsonReader in) throws IOException {
      JsonToken token = in.peek();

      //Log.i(TAG, "read: ------------0" + token.toString());

      switch (token) {
        case BEGIN_ARRAY:
          //Log.i(TAG, "read: ---------BEGIN_ARRAY");
          List<Object> list = new ArrayList<Object>();
          try {
            in.beginArray();
            while (in.hasNext()) {
              list.add(read(in));
            }
            in.endArray();
          } catch (IllegalStateException e) { //如果是空字符串，会有BEGIN_ARRAY报错
            //此时尝试解析成字符串，如果不是空字符串，则依旧抛出异常
            //如果是空字符串，则不抛出异常，使最终返回一个空的列表
            //Log.i(TAG, "read: ---------1");
            if (!"".equals(in.nextString())) {
              throw e;
            }
          }
          return list;

        case BEGIN_OBJECT:
          //Log.i(TAG, "read: ---------BEGIN_OBJECT");
          Map<String, Object> map = new LinkedTreeMap<String, Object>();
          try {
            in.beginObject();
            while (in.hasNext()) {
              map.put(in.nextName(), read(in));
            }
            in.endObject();
          } catch (IllegalStateException e) { //如果是空字符串，会有BEGIN_ARRAY报错
            //此时尝试解析成字符串，如果不是空字符串，则依旧抛出异常
            //如果是空字符串，则不抛出异常，使最终返回一个空的列表
            //Log.i(TAG, "read: ---------2");
            if (!"".equals(in.nextString())) {
              throw e;
            }
          }
          return map;

        case STRING:
          try {
            // Log.i(TAG, "read: ---------STRING");
            if (!"".equals(in.nextString())) {
              return in.nextString();
            } else {
              return null;
            }
          } catch (IllegalStateException e) {
            // Log.i(TAG, "read: ---------3");
            if (!"".equals(in.nextString())) {
              throw e;
            }
            return null;
          }

        case NUMBER:
          //Log.i(TAG, "read: ---------NUMBER");
          return in.nextDouble();

        case BOOLEAN:
          // Log.i(TAG, "read: ---------BOOLEAN");
          return in.nextBoolean();

        case NULL:
          // Log.i(TAG, "read: ---------NULL");
          in.nextNull();
          return null;

        default:
          throw new IllegalStateException();
      }
    }

    @SuppressWarnings("unchecked") @Override public void write(JsonWriter out, Object value)
        throws IOException {
      if (value == null) {
        out.nullValue();
        return;
      }

      TypeAdapter<Object> typeAdapter = (TypeAdapter<Object>) gson.getAdapter(value.getClass());
      if (typeAdapter instanceof Adapter) {
        out.beginObject();
        out.endObject();
        return;
      }

      typeAdapter.write(out, value);
    }
  }
}