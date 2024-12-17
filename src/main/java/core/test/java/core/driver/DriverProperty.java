package core.test.java.core.driver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import core.test.java.core.enumobject.EnumObject.*;
import core.test.java.core.utilities.JsonUtilities;
import org.openqa.selenium.remote.DesiredCapabilities;


public class DriverProperty {

	private URL remoteUrl;
	private String platform;
	private DriverType driverType;
	private RunningMode mode;
	private DesiredCapabilities capabilities;
	private List<String> arguments;
	private String provider;

	public DesiredCapabilities getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(String capabilities) {
		this.capabilities = JsonUtilities.convertJsonToCapabilities(capabilities);
	}

	public RunningMode getMode() {
		return mode;
	}

	public void setMode(RunningMode mode) {
		this.mode = mode;
	}

	public boolean isRemoteMode() {
		if (getMode() != null && getMode() == RunningMode.Remote)
			return true;
		return false;
	}

	public URL getRemoteUrl() {
		return remoteUrl;
	}

	public void setRemoteUrl(String remoteUrl) throws MalformedURLException {
		if (remoteUrl != null)
			this.remoteUrl = new URL(remoteUrl);
	}

	public DriverType getDriverType() {
		return driverType;
	}

	public void setDriverType(DriverType driverType) {
		this.driverType = driverType;
	}

	/**
	 * @return the arguments
	 */
	public List<String> getArguments() {
		return arguments;
	}

	/**
	 * @param arguments the arguments to set
	 */
	public void setArguments(String arguments) {
		this.arguments = JsonUtilities.convertJsonToArguments(arguments);
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

}
