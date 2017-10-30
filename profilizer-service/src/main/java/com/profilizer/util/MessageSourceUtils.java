package com.profilizer.util;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.i18n.LocaleContextHolder;

public class MessageSourceUtils {
	
	private static final Logger LOGGER = Logger.getLogger(MessageSourceUtils.class);

	public static String getMessage(MessageSource messageSource, MessageSourceResolvable resolvable) {
		try
		{
			return messageSource.getMessage(resolvable, LocaleContextHolder.getLocale());
		}
		catch (Exception exception) {
			LOGGER.log(Level.WARN, "Unable to get message: " + exception.getMessage(), exception);
			
			String[] codes = resolvable.getCodes();
			return (codes != null) && (codes.length > 0) ? codes[0] : null;
		}
	}
	
	public static String getMessage(MessageSource messageSource, String code, Object... values) {
		try
		{
			return messageSource.getMessage(code, values, LocaleContextHolder.getLocale());
		}
		catch (Exception exception) {
			LOGGER.log(Level.WARN, "Unable to get message: " + exception.getMessage(), exception);
			
			return code;
		}
	}
}
