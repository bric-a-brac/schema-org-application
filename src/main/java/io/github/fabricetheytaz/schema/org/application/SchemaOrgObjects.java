package io.github.fabricetheytaz.schema.org.application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.function.UnaryOperator;
import io.github.fabricetheytaz.application.SimpleConsoleApplication;
import io.github.fabricetheytaz.schema.org.AutoRepair;
import io.github.fabricetheytaz.schema.org.ContactPoint;
import io.github.fabricetheytaz.schema.org.HairSalon;
import io.github.fabricetheytaz.schema.org.LocalBusiness;
import io.github.fabricetheytaz.schema.org.Organization;
import io.github.fabricetheytaz.schema.org.PostalAddress;
import io.github.fabricetheytaz.schema.org.Thing;
import io.github.fabricetheytaz.schema.org.database.SchemaOrgDatabase;

/**
 * @version 0.1.0
 * @since 0.1.0
 */
public abstract class SchemaOrgObjects extends SimpleConsoleApplication
	{
	protected final SchemaOrgDatabase database;

	/**
	 * @since 0.1.0
	 */
	protected SchemaOrgObjects() throws IOException, SQLException
		{
		super();

		database = new SchemaOrgDatabase();
		}

	/**
	 * @since 0.1.0
	 */
	protected final UnaryOperator<Thing> fillThing = thing ->
		{
		return thing;
		};

	/**
	 * @since 0.1.0
	 */
	protected final UnaryOperator<ContactPoint> fillContactPoint = contactPoint ->
		{
		return contactPoint;
		};

	/**
	 * @since 0.1.0
	 */
	protected final UnaryOperator<PostalAddress> fillPostalAddress = postalAddress ->
		{
		return postalAddress;
		};

	/**
	 * @since 0.1.0
	 */
	protected final UnaryOperator<Organization> fillOrganization = organization ->
		{
		return organization;
		};

	/**
	 * @since 0.1.0
	 */
	protected final UnaryOperator<LocalBusiness> fillLocalBusiness = localBusiness ->
		{
		return localBusiness;
		};

	/**
	 * @since 0.1.0
	 */
	public final <T extends PostalAddress> Thing newPostalAddress(final T postalAddress)
		{
		return fillPostalAddress.andThen(fillContactPoint).andThen(fillThing).apply(postalAddress);
		}

	/**
	 * @since 0.1.0
	 */
	public final <T extends LocalBusiness> Thing newLocalBusiness(final T localBusiness)
		{
		return fillLocalBusiness.andThen(fillOrganization).andThen(fillThing).apply(localBusiness);
		}

	/**
	 * @since 0.1.0
	 */
	public final Thing newAutoRepair()
		{
		return newLocalBusiness(new AutoRepair());
		}

	/**
	 * @since 0.1.0
	 */
	public final Thing newHairSalon()
		{
		return newLocalBusiness(new HairSalon());
		}

	@Override
	public void close()
		{
		super.close();

		try
			{
			database.close();
			}
		catch (final SQLException ex)
			{
			throw new RuntimeException(ex);
			}
		}
	}
