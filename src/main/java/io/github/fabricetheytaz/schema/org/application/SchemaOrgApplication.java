package io.github.fabricetheytaz.schema.org.application;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import io.github.fabricetheytaz.application.SimpleConsoleApplication;
import io.github.fabricetheytaz.schema.org.SchemaOrg;
import io.github.fabricetheytaz.schema.org.database.SchemaOrgDatabase;
import io.github.fabricetheytaz.schema.org.types.Thing;

/**
 * Application en mode console pour gérer la base de données SQLite d'objects Schema.org.
 * 
 * @version 0.1.0
 * @since 0.1.0
 */
public final class SchemaOrgApplication extends SimpleConsoleApplication implements AutoCloseable
	{
	private final SchemaOrgDatabase database;

	/**
	 * @since 0.1.0
	 */
	private SchemaOrgApplication() throws SQLException
		{
		super();

		database = new SchemaOrgDatabase();
		}

	/**
	 * @since 0.1.0
	 */
	public void run() throws IOException, SQLException
		{
		println("SchemaOrgApplication v0.1.0 :)", Color.YELLOW);

		//insert("HairSalon");

		// TODO: Update ID... en cours
		//final String sql = "UPDATE `thing` SET `json` = JSON_SET(`json`, '$.@id', `id`)";

		//ThousandThingsGoal.goal(this);
		}

	/*
	public Thing create(final String type)
		{
		try
			{
			final Thing thing = SchemaOrg.create(type);

			fill(thing);

			return thing;
			}
		catch (final ReflectionException ex)
			{
			error("ERROR create() " + ex.getMessage());

			return null;
			}
		}
	*/

	/*
	public void fill(final Thing thing)
		{
		SchemaOrg.setters(thing, (method, property) ->
			{
			setString(property.label(), method, thing);
			});
		}
	*/

	/*
	public void insert(final String type) throws IOException, SQLException
		{
		final Thing thing = create(type);

		if (thing != null)
			{
			database.insert(thing);
			}
		}
	*/

	/**
	 * @since 0.1.0
	 */
	@Override
	public void close() throws SQLException
		{
		database.close();
		}

	/**
	 * @since 0.1.0
	 */
	private void setString(final String label, final Method method, final Thing thing)
		{
		print(label + ": ");

		final String value = getString();

		if (value != null)
			{
			try
				{
				method.invoke(thing, value);
				}
			catch (final IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
				{
				error(ex.getMessage());
				}
			}
		}
	}
