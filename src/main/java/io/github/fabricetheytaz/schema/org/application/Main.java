package io.github.fabricetheytaz.schema.org.application;

import org.apache.commons.cli.Options;

public final class Main
	{
	/**
	 * @since 0.1.0
	 */
	private Main()
		{
		throw new UnsupportedOperationException();
		}

	/**
	 * @since 0.1.0
	 */
	public static void main(final String[] args)
		{
		final Options options = new Options();

		// schemaorg --new HairSalon
		// schemaorg -n HairSalon

		//options.addOption(null, null, false, null);
		/*
		try (final SchemaOrgApplication application = new SchemaOrgApplication())
			{
			//application.run();
			}
		catch (final Exception ex)
			{
			ex.printStackTrace();
			}
		*/
		}
	}
