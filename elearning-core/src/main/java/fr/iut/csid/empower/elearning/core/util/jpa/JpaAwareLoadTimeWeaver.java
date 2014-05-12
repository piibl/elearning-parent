package fr.iut.csid.empower.elearning.core.util.jpa;

import org.springframework.instrument.classloading.SimpleLoadTimeWeaver;

public class JpaAwareLoadTimeWeaver extends SimpleLoadTimeWeaver {
	@Override
	public ClassLoader getInstrumentableClassLoader() {
		ClassLoader instrumentableClassLoader = super.getInstrumentableClassLoader();
		if (instrumentableClassLoader.getClass().getName().endsWith("SimpleInstrumentableClassLoader")) {
			return instrumentableClassLoader.getParent();
		} else {
			return instrumentableClassLoader;
		}
	}
}