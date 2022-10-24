package ru.hzerr.fx.framework.logging.factory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.encoder.LayoutWrappingEncoder;
import org.slf4j.LoggerFactory;
import ru.hzerr.fx.framework.logging.encoder.ColoredPatternLayoutEncoder;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ConsoleLogFactory extends LogFactory {

    private final LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
    private final PatternLayout consoleEncoder = new ColoredPatternLayoutEncoder();
    private final LayoutWrappingEncoder<ILoggingEvent> wrappingConsoleEncoder = new LayoutWrappingEncoder<>();
    private final ConsoleAppender<ILoggingEvent> consoleAppender = new ConsoleAppender<>();

    public ConsoleLogFactory() {
    }

    public void createConsoleSession() {
        log = lc.getLogger("GallantBusinessEnterpriseConsoleLogger");
        String formattedTime = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(Calendar.getInstance().getTime());
        consoleEncoder.setContext(lc);
        consoleEncoder.setPattern("[%thread] %highlight(%-5level) | %time(" + formattedTime + ") | - %highlight(%msg) %n");
        consoleEncoder.start();

        consoleAppender.setContext(lc);
        consoleAppender.setName("console");
        wrappingConsoleEncoder.setLayout(consoleEncoder);
        wrappingConsoleEncoder.setCharset(StandardCharsets.UTF_8);
        consoleAppender.setEncoder(wrappingConsoleEncoder);
        consoleAppender.start();

        log.setAdditive(false);
        log.setLevel(Level.DEBUG);
        log.addAppender(consoleAppender);
    }

    @Override
    public void close() {
        consoleEncoder.stop();
        wrappingConsoleEncoder.stop();
        consoleAppender.stop();
        lc.stop();
    }
}
