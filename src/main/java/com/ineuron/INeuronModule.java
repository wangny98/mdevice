package com.ineuron;

import javax.inject.Named;

import com.google.inject.AbstractModule;

import com.google.inject.Provides;


public class INeuronModule extends AbstractModule {

	
	@Override
	protected void configure() {

	}

	
	@Provides
	@Named("environment")
	public String provideEnvironment(INeuronConfiguration configuration) {
		return configuration.getEnvironment();

	}
	
}
