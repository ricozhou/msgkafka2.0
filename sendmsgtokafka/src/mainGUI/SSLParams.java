package mainGUI;

public class SSLParams {
	// keystore location
	public String keystoreLocation;
	// pwd
	public String keystorePwd;

	// truststore location
	public String truststoreLocation;
	// pwd
	public String truststorePwd;
	public String keyPwd;

	public String getKeystoreLocation() {
		return keystoreLocation;
	}

	public void setKeystoreLocation(String keystoreLocation) {
		this.keystoreLocation = keystoreLocation;
	}

	public String getKeystorePwd() {
		return keystorePwd;
	}

	public void setKeystorePwd(String keystorePwd) {
		this.keystorePwd = keystorePwd;
	}

	public String getTruststoreLocation() {
		return truststoreLocation;
	}

	public void setTruststoreLocation(String truststoreLocation) {
		this.truststoreLocation = truststoreLocation;
	}

	public String getTruststorePwd() {
		return truststorePwd;
	}

	public void setTruststorePwd(String truststorePwd) {
		this.truststorePwd = truststorePwd;
	}

	public String getKeyPwd() {
		return keyPwd;
	}

	public void setKeyPwd(String keyPwd) {
		this.keyPwd = keyPwd;
	}

	@Override
	public String toString() {
		return "SSLParams [keystoreLocation=" + keystoreLocation + ", keystorePwd=" + keystorePwd
				+ ", truststoreLocation=" + truststoreLocation + ", truststorePwd=" + truststorePwd + ", keyPwd="
				+ keyPwd + "]";
	}
}
