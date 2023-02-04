package io.github.fabricetheytaz.schema.org.application;

import java.io.IOException;
import java.sql.SQLException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.fabricetheytaz.schema.org.database.SchemaOrgDatabase;
import io.github.fabricetheytaz.schema.org.types.Thing;

/**
 * @version 0.1.0
 * @since 0.1.0
 */
public final class ShowThings
	{
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

	public static void show(final Thing thing)
		{
		System.out.println(GSON.toJson(thing));
		}

	/**
	 * @since 0.1.0
	 */
	public static <T extends Thing> void show(final Integer id, final T thing) throws IOException
		{
		System.out.println(id + ") " + thing.getName());
		}

	/**
	 * @since 0.1.0
	 */
	public static <T extends Thing> void show(final SchemaOrgDatabase database, final Class<T> classOfT) throws IOException, SQLException
		{
		database.getAll(classOfT, ShowThings::show);
		}
	}
