package io.github.fabricetheytaz.schema.org.application;

import java.io.IOException;
import java.sql.SQLException;
import org.apache.commons.lang3.function.FailableRunnable;

/**
 * Application en mode console pour gérer la base de données SQLite d'objects Schema.org.
 * 
 * @version 0.1.0
 * @since 0.1.0
 */
public final class SchemaOrgApplication extends SchemaOrgObjects implements FailableRunnable<Exception>
	{
	/**
	 * @since 0.1.0
	 */
	private SchemaOrgApplication() throws IOException, SQLException
		{
		super();
		}

	/**
	 * @since 0.1.0
	 */
	@Override
	public void run() throws Exception
		{
		println("SchemaOrgApplication v0.1.0 :)", Color.YELLOW);

		// TODO: Update ID... en cours
		//final String sql = "UPDATE `thing` SET `json` = JSON_SET(`json`, '$.@id', `id`)";

		InsertThing.insert(this, "HairSalon2");

		//ShowThings.show(newAutoRepair());
		}

	/**
	 * @since 0.1.0
	 */
	public static void main(final String[] args)
		{
		try (final SchemaOrgApplication application = new SchemaOrgApplication())
			{
			application.run();
			}
		catch (final Exception ex)
			{
			ex.printStackTrace();
			}
		}
	}
