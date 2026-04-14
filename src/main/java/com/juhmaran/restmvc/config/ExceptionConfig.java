package com.juhmaran.restmvc.config;

import br.com.juhmaran.exception.handler.GlobalExceptionHandler;
import br.com.juhmaran.exception.util.TraceFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * spring-rest-mvc
 *
 * @author Juliane Maran
 * @since 14/04/2026
 */
@Configuration
@Import({GlobalExceptionHandler.class, TraceFilter.class})
public class ExceptionConfig {

}
