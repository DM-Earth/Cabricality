package com.dm.earth.cabricality.config;

import net.krlite.pierced.annotation.Table;
import net.krlite.pierced.config.Pierced;
import org.quiltmc.loader.api.QuiltLoader;

import java.io.File;

public class CabfConfig extends Pierced {
	private static final File file = QuiltLoader.getConfigDir().resolve("cabricality.toml").toFile();

	public CabfConfig() {
		super(CabfConfig.class, file);
		load();
	}

	private boolean includeVersionInWindowTitle = false;

	public boolean includeVersionInWindowTitle() {
		return includeVersionInWindowTitle;
	}

	public void includeVersionInWindowTitle(boolean includeVersionInGameTitle) {
		this.includeVersionInWindowTitle = includeVersionInGameTitle;
		save();
	}

	private boolean backgroundBlur = false;

	public boolean backgroundBlur() {
		return backgroundBlur;
	}

	public void backgroundBlur(boolean backgroundBlur) {
		this.backgroundBlur = backgroundBlur;
		save();
	}

	private boolean backgroundBlurDarken = true;

	public boolean backgroundBlurDarken() {
		return backgroundBlurDarken;
	}

	public void backgroundBlurDarken(boolean backgroundBlurDarken) {
		this.backgroundBlurDarken = backgroundBlurDarken;
		save();
	}

	private float backgroundBlurRadius = 9;

	public float backgroundBlurRadius() {
		return backgroundBlurRadius;
	}

	public void backgroundBlurRadius(float backgroundBlurRadius) {
		this.backgroundBlurRadius = backgroundBlurRadius;
		save();
	}

	@Table("debug")
	private boolean debugInfo = false;

	public boolean debugInfo() {
		return debugInfo;
	}

	public void debugInfo(boolean debugInfo) {
		this.debugInfo = debugInfo;
		save();
	}

	@Table("debug")
	private boolean cleanerLog = true;

	public boolean cleanerLog() {
		return cleanerLog;
	}

	public void cleanerLog(boolean cleanerLog) {
		this.cleanerLog = cleanerLog;
		save();
	}
}
