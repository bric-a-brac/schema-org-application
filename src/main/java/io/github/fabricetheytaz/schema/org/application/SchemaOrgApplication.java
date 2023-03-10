package io.github.fabricetheytaz.schema.org.application;

import java.io.IOException;
import java.sql.SQLException;
import org.apache.commons.lang3.function.FailableRunnable;
import io.github.fabricetheytaz.schema.org.HairSalon;

/**
 * Application en mode console pour gérer la base de données SQLite d'objects Schema.org.
 * 
 * @version 0.1.0
 * @since 0.1.0
 */
public final class SchemaOrgApplication extends SchemaOrgObjects implements FailableRunnable<Exception>
	{
	protected SchemaOrgApplication() throws IOException, SQLException
		{
		super();
		}

	@Override
	public void run() throws Exception
		{
		println("SchemaOrgApplication v0.1.0 :)", Color.YELLOW);

		// TODO: Update ID
		//final String sql = "UPDATE `thing` SET `json` = JSON_SET(`json`, '$.@id', `id`)";
		//database.insert(newLocalBusiness(new AutoRepair()));

		database.getAll(HairSalon.class, (id, hairSalon) ->
			{
			System.out.println(hairSalon.getName());
			});
		}

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
