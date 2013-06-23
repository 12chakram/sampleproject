package com.eng.gp.project.util.date;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Captures type information before Java has a chance to erase it.
 * 
 * Based on <a
 * href="http://gafter.blogspot.com/2006/12/super-type-tokens.html">http
 * ://gafter.blogspot.com/2006/12/super-type-tokens.html</a> and <a href=
 * "http://gafter.blogspot.com/2006/12/super-type-tokens.html?showComment=1171980720000#c2954512480577635806"
 * >http://gafter.blogspot.com/2006/12/super-type-tokens.html?showComment=
 * 1171980720000#c2954512480577635806</a>
 * 
 */
public class TypeReference<T> implements Comparable<TypeReference<T>> {

	private final Type type;
	private volatile Constructor<?> constructor;

	protected TypeReference() {
		Type superclass = getClass().getGenericSuperclass();
		if (superclass instanceof Class) {
			throw new RuntimeException("Missing type parameter.");
		}
		this.type = ((ParameterizedType) superclass).getActualTypeArguments()[0];
	}

	/**
	 * Instantiates a new instance of {@code T} using the default, no-arg
	 * constructor.
	 */
	@SuppressWarnings("unchecked")
	public T newInstance() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		if (constructor == null) {
			Class<?> rawType = type instanceof Class<?> ? (Class<?>) type : (Class<?>) ((ParameterizedType) type).getRawType();
			constructor = rawType.getConstructor();
		}
		return (T) constructor.newInstance();
	}

	/**
	 * Gets the referenced type.
	 */
	public Type getType() {
		return this.type;
	}

	/**
	 * Dummy implementation to require that generic type info is specified.
	 */
	@Override
	public int compareTo(TypeReference<T> arg0) {
		return 0;
	}

}
