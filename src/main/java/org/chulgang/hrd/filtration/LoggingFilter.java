package org.chulgang.hrd.filtration;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.chulgang.hrd.util.PerformanceMeasurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.chulgang.hrd.util.LogConstant.PERFORMANCE_MEASUREMENT;


@WebFilter(urlPatterns = "/")
public class LoggingFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);

    private static final String REQUEST_RECIEVED_MESSAGE = "Received request for '{}'";
    private static final String REQUEST_COMPLETE_MESSAGE
            = "Completed request for '{}', status code: {}, ";

    private static final String STARTED_AT = "startedAt";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        log.info(REQUEST_RECIEVED_MESSAGE, httpServletRequest.getRequestURI());

        long beforeMemory = PerformanceMeasurer.computeUsedMemory(0);
        long startedAt = PerformanceMeasurer.computeElapsedTime(0);
        request.setAttribute(STARTED_AT, startedAt);

        try {
            chain.doFilter(request, response);
        } finally {
            long elapsedTime = PerformanceMeasurer.computeElapsedTime(startedAt);
            long memoryUsage = PerformanceMeasurer.computeUsedMemory(beforeMemory);

            log.info(REQUEST_COMPLETE_MESSAGE + PERFORMANCE_MEASUREMENT,
                    httpServletRequest.getRequestURI(), httpServletResponse.getStatus(), elapsedTime, memoryUsage);
        }
    }
}
