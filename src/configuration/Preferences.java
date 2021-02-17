package configuration;

public abstract class Preferences {

	public Preferences() {
		// Intentionally empty.
	}

	public abstract String getType();

	protected abstract void initialize(ConfigurationProcessor config);

	protected abstract void validate();

}
