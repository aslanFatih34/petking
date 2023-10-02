package com.fatih.petking.infrastructure.locale;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@RequiredArgsConstructor
@Slf4j
@Component
public class MessageSourceLocalize {

    private final ResourceBundleMessageSource messageSource;

    public String getLocaleMessage(String key, Object... args) {
        try {
            Locale locale = LocaleContextHolder.getLocale();
            return messageSource.getMessage(key, args, locale);
        } catch (NoSuchMessageException e) {
            log.error(key + " not found in messages file", e);
            return StringUtils.EMPTY;
        }
    }
}
