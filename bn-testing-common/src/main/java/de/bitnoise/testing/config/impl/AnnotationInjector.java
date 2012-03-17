package de.bitnoise.testing.config.impl;

import java.lang.reflect.Field;

import de.bitnoise.testing.config.ObjectInjector;
import de.bitnoise.testing.config.TestConfigProperty;
import de.bitnoise.testing.config.ObjectTranslator;

public class AnnotationInjector implements ObjectInjector {

	@Override
	public void load(ObjectTranslator source, Object target) {
		if (target == null) {
			return;
		}
		Injector injector;
		if (target instanceof Class) {
			injector = new Injector(null, (Class<?>) target);
		} else {
			injector = new Injector(target, target.getClass());
		}
		injector.injectWith(source);
	}

	protected class Injector {

		protected Object _target;
		protected Class<?> _clazz;
		protected ObjectTranslator _source;

		public Injector(Object target, Class<?> clazz) {
			this._target = target;
			this._clazz = clazz;
		}

		public void injectWith(ObjectTranslator source) {
			this._source = source;

			findAnnotations(_clazz);
		}

		protected void findAnnotations(Class<?> clazz) {
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				TestConfigProperty annotation = field.getAnnotation(TestConfigProperty.class);
				if (annotation != null) {
					injectField(field, annotation);
				}
			}
			Class<?> superClazz = clazz.getSuperclass();
			if (!superClazz.equals(Object.class)) {
				findAnnotations(superClazz);
			}
		}

		protected void injectField(Field field, TestConfigProperty annotation) {
			String key = annotation.value();
			if (key == null || key.isEmpty()) {
				return;
			}
			Object value = _source.getValueFor(key, field.getType());
			doSetField(field, value);
		}

		protected void doSetField(Field field, Object value) {
			field.setAccessible(true);
			try {
				field.set(_target, value);
			} catch (IllegalAccessException e) {
				throw new RuntimeException();
			}
		}
	}

}
