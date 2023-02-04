package io.github.fabricetheytaz.schema.org.application;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import io.github.fabricetheytaz.schema.org.types.Thing;

import static io.github.fabricetheytaz.util.Argument.notNull;

/**
 * @version 0.1.0
 * @since 0.1.0
 */
public final class InsertThing
	{
	public static void insert(final SchemaOrgApplication application, final String type)
		{
		notNull(type);

		final Class<?> clazz = notNull(application).getClass();

		try
			{
			final Method newThing = clazz.getMethod("new" + type);

			try
				{
				final Thing thing = (Thing) newThing.invoke(application);
				ShowThings.show(thing);
				}
			catch (final IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
				{
				application.error(ex.getMessage());
				}
			}
		catch (final NoSuchMethodException ex)
			{
			application.error(String.format("Type '%s' pas encore supporté", type));
			}
		}
	}
