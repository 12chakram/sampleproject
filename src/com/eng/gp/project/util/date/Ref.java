package com.eng.gp.project.util.date;


/**
 * Values referenced from an inner-class must be marked final. By making the reference final rather than the value, we
 * can update the value from the inner-class and reference it from the outside.
 * 
 * This class just represents a reference to a val.
 * 
 * @param <T>
 */
public class Ref<T> {

	/**
	 * Helper method that provides some sugar when constructing {@link Ref}s <br/>
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * import static Refs.ref;
	 * 
	 * Ref&lt;String&gt; myRef = ref("myVal");
	 * </pre>
	 * 
	 * </blockquote>
	 * </p>
	 * 
	 * instead of:
	 * 
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * Ref&lt;String&gt; myRef = new Ref&lt;String&gt;(&quot;myVal&quot;);
	 * </pre>
	 * 
	 * </blockquote>
	 * </p>
	 * 
	 * @param val
	 * @return {@link Ref} to val
	 */
	public static <T> Ref<T> ref(T val) {
		return new Ref<T>(val);
	}

	public Ref() {
		val = null;
	}

	public Ref(T val) {
		this.val = val;
	}

	public T val;
}
