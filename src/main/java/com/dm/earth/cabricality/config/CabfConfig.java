package com.dm.earth.cabricality.config;

import net.krlite.pierced.annotation.Table;
import net.krlite.pierced.config.Pierced;

import java.io.File;

public class CabfConfig extends Pierced {
	public CabfConfig(File file) {
		super(CabfConfig.class, file);
	}

	public boolean backgroundBlur = true;
	public float backgroundBlurRadius = 35;
	@Table("debug")
	public boolean debugInfo = false;
	@Table("debug")
	public boolean cleanerLog = true;
}
