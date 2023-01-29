package io.github.fabricetheytaz.schema.org.application;

import java.util.function.Consumer;
import java.util.function.Supplier;
import io.github.fabricetheytaz.application.SimpleConsoleApplication;
import io.github.fabricetheytaz.schema.org.Thing;

import static io.github.fabricetheytaz.util.Argument.notNull;

public abstract class SchemaOrgObjectsOld extends SimpleConsoleApplication
	{
	/*
	private final void thing(final Thing thing)
		{
		info("Thing");
		setString("Nom", thing, thing::getName, thing::setName);
		setString("Description", thing, thing::getDescription, thing::setDescription);
		}
	*/

	/*
	private final void contactPoint(final ContactPoint contact)
		{
		info("ContactPoint");
		setString("E-Mail", contact, contact::getEmail, contact::setEmail);
		setString("Téléphone", contact, contact::getTelephone, contact::setTelephone);
		}
	*/

	/*
	private final void postalAddress(final PostalAddress address)
		{
		info("PostalAddress");
		setString("Rue", address, address::getStreetAddress, address::setStreetAddress);
		setString("Code postal", address, address::getPostalCode, address::setPostalCode);
		setString("Localité", address, address::getAddressLocality, address::setAddressLocality);
		}
	*/

	/*
	private final void organization(final Organization organization)
		{
		info("Organization");
		thing(organization);
		final ContactPoint contact = new ContactPoint();
		contactPoint(contact);
		organization.setContactPoint(contact);

		//final PostalAddress address = new PostalAddress();
		//postalAddress(address);
		//organization.setAddress(address);

		setPostalAddress("Address", organization, organization::getAddress, organization::setAddress);
		}
	*/

	/*
	public final void busStop(final BusStop busStop)
		{
		info("BusStop");
		thing(busStop);
		setFloat("Latitude", busStop, busStop::getLatitude, busStop::setLatitude);
		setFloat("Longitude", busStop, busStop::getLongitude, busStop::setLongitude);
		}
	*/

	/*
	protected final <T extends LocalBusiness> T newLocalBusiness(final T localBusiness)
		{
		info("New LocalBusiness<T>");
		organization(localBusiness);
		return localBusiness;
		}
	*/

	/*
	protected final BusStop newBusStop()
		{
		final BusStop stop = new BusStop();
		busStop(stop);
		return stop;
		}
	*/

	/*
	private final <T extends Thing> void set(final String label, final T thing, final Supplier<String> getter, final Consumer<String> setter)
		{
		notNull(label);
		notNull(thing);
		notNull(getter);
		notNull(setter);

		System.out.print(label);

		final String currentValue = getter.get();

		if (currentValue != null)
			{
			System.out.print(" (" + currentValue + ")");
			}

		System.out.print(": ");

		String line = null;

		if (scanner.hasNextLine())
			{
			line = scanner.nextLine();

			if (line.isBlank())
				{
				line = null;
				}
			}

		if (line != null)
			{
			setter.accept(line);
			}
		}
	*/

	/**
	 * @since 0.1.0
	 */
	private final <T extends Thing, V> void set(final String label, final T thing, final Supplier<V> value, final Supplier<V> getter, final Consumer<V> setter)
		{
		notNull(label);
		notNull(thing);
		notNull(value);
		notNull(getter);
		notNull(setter);

		System.out.print(label);

		final V currentValue = getter.get();

		if (currentValue != null)
			{
			System.out.print(" (" + currentValue + ")");
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
	protected final <T extends Thing> void setString(final String label, final T thing, final Supplier<String> getter, final Consumer<String> setter)
		{
		set(label, thing, this::getString, getter, setter);
		}

	/**
	 * @since 0.1.0
	 */
	protected final <T extends Thing> void setFloat(final String label, final T thing, final Supplier<Float> getter, final Consumer<Float> setter)
		{
		set(label, thing, this::getFloat, getter, setter);
		}

	/**
	 * @since 0.1.0
	 */
	protected final <T extends Thing> void setDouble(final String label, final T thing, final Supplier<Double> getter, final Consumer<Double> setter)
		{
		set(label, thing, this::getDouble, getter, setter);
		}

	/*
	protected final <T extends Thing> void setPostalAddress(final String label, final T thing, final Supplier<PostalAddress> getter, final Consumer<PostalAddress> setter)
		{
		set(label, thing, this::newPostalAddress, getter, setter);
		}
	*/

	/**
	 * @since 0.1.0
	 */
	@Override
	public final String getString()
		{
		if (scanner.hasNextLine())
			{
			final String line = scanner.nextLine();

			if (!line.isBlank())
				{
				return line;
				}
			}

		return null;
		}

	/**
	 * @since 0.1.0
	 */
	private final Double getDouble()
		{
		if (scanner.hasNextDouble())
			{
			return Double.valueOf(scanner.nextDouble());
			}

		return null;
		}

	/**
	 * @since 0.1.0
	 */
	private final Float getFloat()
		{
		if (scanner.hasNextFloat())
			{
			return Float.valueOf(scanner.nextFloat());
			}

		return null;
		}

	public void dev()
		{
		/*
		//System.out.println(getDouble());
		//System.out.println(getFloat());

		final BusStop stop = new BusStop();

		set("Nom", stop, this::getString, stop::getName, stop::setName);
		//set("Latitude", stop, this::getDouble, stop::getLatitude, stop::setLatitude);
		set("Latitude", stop, this::getFloat, stop::getLatitude, stop::setLatitude);
		stop.setLongitude(34F);

		String json = new JSONRenderer().render(stop);
		System.out.println(json);
		*/
		}
	}
