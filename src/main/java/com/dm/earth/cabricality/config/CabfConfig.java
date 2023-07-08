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

	public boolean backgroundBlur = false;

	public boolean backgroundBlurDarken = true;

	public float backgroundBlurRadius = 9;

	@Table("debug")
	public boolean debugInfo = false;

	@Table("debug")
	public boolean cleanerLog = true;
}
