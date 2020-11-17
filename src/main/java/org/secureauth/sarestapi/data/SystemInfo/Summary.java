package org.secureauth.sarestapi.data.SystemInfo;

public class Summary{
	private int patchLevel;
	private boolean patched;

	public int getPatchLevel() {
		return patchLevel;
	}

	public void setPatchLevel(int patchLevel) {
		this.patchLevel = patchLevel;
	}

	public boolean getPatched() {
		return patched;
	}

	public void setPatched(boolean patched) {
		this.patched = patched;
	}
}