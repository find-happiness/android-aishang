package com.aishang.app.util.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by song on 2016/3/7.
 */
public class EmptyStringArrayTypeAdapterFactory implements TypeAdapterFactory {
  @SuppressWarnings("unchecked")
  public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
    Type type = typeToken.getType();
    if (!(type instanceof GenericArrayType
        || type instanceof Class && ((Class<?>) type).isArray())) {
      return null;
    }

    Type componentType = $Gson$Types.getArrayComponentType(type);
    TypeAdapter<?> componentTypeAdapter = gson.getAdapter(TypeToken.get(componentType));
    return new ArrayNullAdapter(gson, componentTypeAdapter, $Gson$Types.getRawType(componentType));
  }

  public class ArrayNullAdapter<E> extends TypeAdapter<Object> {

    private final Class<E> componentType;
    private final TypeAdapter<E> componentTypeAdapter;

    public ArrayNullAdapter(Gson context, TypeAdapter<E> componentTypeAdapter,
        Class<E> componentType) {
      this.componentTypeAdapter =
          new TypeAdapterRuntimeTypeWrapper<E>(context, componentTypeAdapter, componentType);
      this.componentType = componentType;
    }

    public Object read(JsonReader in) throws IOException {
      if (in.peek() == JsonToken.NULL) {
        in.nextNull();
        return null;
      }
      List<E> list = new ArrayList<E>();
      try{
        in.beginArray();
        while (in.hasNext()) {
          E instance = componentTypeAdapter.read(in);
          list.add(instance);
        }
        in.endArray();
      }
      catch (IllegalStateException e){ //如果是空字符串，会有BEGIN_ARRAY报错
        //此时尝试解析成字符串，如果不是空字符串，则依旧抛出异常
        //如果是空字符串，则不抛出异常，使最终返回一个空的列表
        if (!"".equals(in.nextString())){
          throw e;
        }
      }

      Object array = Array.newInstance(componentType, list.size());
      for (int i = 0; i < list.size(); i++) {
        Array.set(array, i, list.get(i));
      }
      return array;
    }

    @SuppressWarnings("unchecked") @Override public void write(JsonWriter out, Object array)
        throws IOException {
      if (array == null) {
        out.nullValue();
        return;
      }

      out.beginArray();
      for (int i = 0, length = Array.getLength(array); i < length; i++) {
        E value = (E) Array.get(array, i);
        componentTypeAdapter.write(out, value);
      }
      out.endArray();
    }
  }
}