/*
 * generated by Xtext
 */
package org.eclipse.b3;

import org.eclipse.xtext.conversion.IValueConverterService;

//import org.eclipse.xtext.conversion.IValueConverterService;

/**
 * Use this class to register components to be used within the IDE.
 */
public class BeeLangRuntimeModule extends org.eclipse.b3.AbstractBeeLangRuntimeModule {

//	Supply the class that does all the value conversion.
//@Override
//public Class<? extends IValueConverterService> bindIValueConverterService() {
//	// TODO Auto-generated method stub
//	return super.bindIValueConverterService();
//}
@Override
public Class<? extends IValueConverterService> bindIValueConverterService() {
		return BeeLangTerminalConverters.class;
	}
}