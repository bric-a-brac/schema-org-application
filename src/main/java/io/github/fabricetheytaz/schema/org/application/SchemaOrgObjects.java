package io.github.fabricetheytaz.schema.org.application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import io.github.fabricetheytaz.application.SimpleConsoleApplication;
import io.github.fabricetheytaz.schema.org.database.SchemaOrgDatabase;

import io.github.fabricetheytaz.schema.org.types.*;

import static io.github.fabricetheytaz.util.Argument.notNull;

/**
 * @version 0.1.0
 * @since 0.1.0
 */
public abstract class SchemaOrgObjects extends SimpleConsoleApplication implements AutoCloseable
	{
	protected final SchemaOrgDatabase database;

	/**
	 * @since 0.1.0
	 */
	public SchemaOrgObjects() throws IOException, SQLException
		{
		super();

		database = new SchemaOrgDatabase();
		}

	/**
	 * @since 0.1.0
	 */
	protected final UnaryOperator<Thing> fillThing = thing ->
		{
		info("Thing");

		setStringProperty("Nom", thing, thing::getName, thing::setName);
		setStringProperty("Description", thing, thing::getDescription, thing::setDescription);

		return thing;
		};

	/**
	 * @since 0.1.0
	 */
	protected final UnaryOperator<ContactPoint> fillContactPoint = contact ->
		{
		info("ContactPoint");

		setStringProperty("E-Mail", contact, contact::getEmail, contact::setEmail);
		setStringProperty("Téléphone", contact, contact::getTelephone, contact::setTelephone);

		return contact;
		};

	/**
	 * @since 0.1.0
	 */
	protected final UnaryOperator<PostalAddress> fillPostalAddress = address ->
		{
		info("PostalAddress");

		setStringProperty("Rue", address, address::getStreetAddress, address::setStreetAddress);
		setStringProperty("Code postal", address, address::getPostalCode, address::setPostalCode);
		setStringProperty("Localité", address, address::getAddressLocality, address::setAddressLocality);

		return address;
		};

	/**
	 * @since 0.1.0
	 */
	protected final UnaryOperator<Organization> fillOrganization = organization ->
		{
		/*
		info("Organization");
		thing(organization);
		final ContactPoint contact = new ContactPoint();
		contactPoint(contact);
		organization.setContactPoint(contact);

		//final PostalAddress address = new PostalAddress();
		//postalAddress(address);
		//organization.setAddress(address);

		setPostalAddress("Address", organization, organization::getAddress, organization::setAddress);
		*/

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

	/**
	 * @since 0.1.0
	 */
	@Override
	public final void close() throws SQLException
		{
		database.close();
		}

	/**
	 * @since 0.1.0
	 */
	private final <T extends Thing, V> void setProperty(final String name, final T thing, final Supplier<V> value, final Supplier<V> getter, final Consumer<V> setter)
		{
		notNull(name);
		notNull(thing);
		notNull(value);
		notNull(getter);
		notNull(setter);

		System.out.print(name);

		final V oldValue = getter.get();

		if (oldValue != null)
			{
			System.out.print(" (" + oldValue + ")");
			}

		System.out.print(": ");

		final V newValue = value.get();

		if (newValue != null)
			{
			setter.accept(newValue);
			}
		}

	/**
	 * @since 0.1.0
	 */
	private final <T extends Thing> void setStringProperty(final String name, final T thing, final Supplier<String> getter, final Consumer<String> setter)
		{
		setProperty(name, thing, this::getString, getter, setter);
		}

	/**
	 * @since 0.1.0
	 */
	@SuppressWarnings("unused")
	private final <T extends Thing> void setFloatProperty(final String name, final T thing, final Supplier<Float> getter, final Consumer<Float> setter)
		{
		setProperty(name, thing, this::getFloat, getter, setter);
		}

	/**
	 * @since 0.1.0
	 */
	@SuppressWarnings("unused")
	private final <T extends Thing> void setDoubleProperty(final String name, final T thing, final Supplier<Double> getter, final Consumer<Double> setter)
		{
		setProperty(name, thing, this::getDouble, getter, setter);
		}

	/*
	protected final <T extends Thing> void setPostalAddress(final String label, final T thing, final Supplier<PostalAddress> getter, final Consumer<PostalAddress> setter)
		{
		set(label, thing, this::newPostalAddress, getter, setter);
		}
	*/
	}
