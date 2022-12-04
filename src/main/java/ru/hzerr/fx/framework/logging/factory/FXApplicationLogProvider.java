package ru.hzerr.fx.framework.logging.factory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.encoder.LayoutWrappingEncoder;
import ch.qos.logback.core.rolling.RollingFileAppender;
import org.slf4j.LoggerFactory;
import ru.hzerr.file.BaseFile;
import ru.hzerr.file.SizeType;
import ru.hzerr.fx.framework.core.a.configuration.ILoggingConfiguration;
import ru.hzerr.fx.framework.core.a.configuration.IStructureConfiguration;
import ru.hzerr.fx.framework.core.annotation.Inject;
import ru.hzerr.fx.framework.core.annotation.LogProvider;
import ru.hzerr.fx.framework.core.annotation.Tag;
import ru.hzerr.fx.framework.exception.ConfigurableException;
import ru.hzerr.fx.framework.exception.unchecked.FactoryCloseableException;
import ru.hzerr.fx.framework.logging.policy.CancelRollingPolicy;

import java.io.IOException;

@LogProvider
@Tag("applicationLogProvider")
public class FXApplicationLogProvider implements ILogProvider {

    @Inject
    private ILoggingConfiguration applicationLoggingConfiguration;
    @Inject
    private IStructureConfiguration structureConfiguration;

    private final BaseFile sessionLogFile = structureConfiguration.getLogDirectory().getSubFile(applicationLoggingConfiguration.getLogFileName());
    private final LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
    private final PatternLayout consolePatternLayout = applicationLoggingConfiguration.getConsolePatternLayout();
    private final LayoutWrappingEncoder<ILoggingEvent> consoleEncoder = new LayoutWrappingEncoder<>();
    private final ConsoleAppender<ILoggingEvent> consoleAppender = new ConsoleAppender<>();
    private final CancelRollingPolicy filePolicy = new CancelRollingPolicy();
    private final PatternLayoutEncoder fileEncoder = new PatternLayoutEncoder();
    private final RollingFileAppender<ILoggingEvent> fileAppender = new RollingFileAppender<>();

    private final Logger log = lc.getLogger(applicationLoggingConfiguration.getLoggerName());

    @Override
    public Logger getLogger() {
        return log;
    }

    @Override
    public void close() throws IOException {
        filePolicy.stop();
        fileEncoder.stop();
        fileAppender.stop();
        consolePatternLayout.stop();
        consoleEncoder.stop();
        consoleAppender.stop();
        lc.stop();
        if (sessionLogFile != null) {
            if (sessionLogFile.sizeOf(SizeType.BYTE) == 0D) {
                sessionLogFile.delete();
            }
        }
    }

    @Override
    public void configure() throws ConfigurableException {
        if (applicationLoggingConfiguration.isEnabled()) {
            if (applicationLoggingConfiguration.isConsoleLoggingEnabled()) {
                consolePatternLayout.setContext(lc);
                consolePatternLayout.setPattern(applicationLoggingConfiguration.getLoggerConsolePattern());
                consolePatternLayout.start();

                consoleEncoder.setLayout(consolePatternLayout);
                consoleEncoder.setCharset(applicationLoggingConfiguration.getConsoleEncoding());

                consoleAppender.setContext(lc);
                consoleAppender.setEncoder(consoleEncoder);
                consoleAppender.start();

                log.addAppender(consoleAppender);
            }

            if (applicationLoggingConfiguration.isFileLoggingEnabled()) {
                if (sessionLogFile.notExists())
                    throw new ConfigurableException("Unable to create a log file of the current session");

                filePolicy.setContext(lc);
                filePolicy.setParent(fileAppender);
                filePolicy.setFileNamePattern("%d{yyyy-MM-dd HH-mm-ss}.log");
                filePolicy.start();

                fileEncoder.setContext(lc);
                fileEncoder.setCharset(applicationLoggingConfiguration.getFileEncoding());
                fileEncoder.setPattern(applicationLoggingConfiguration.getLoggerFilePattern());
                fileEncoder.start();

                fileAppender.setContext(lc);
                fileAppender.setEncoder(fileEncoder);
                fileAppender.setAppend(true);
                fileAppender.setFile(sessionLogFile.getLocation());
                fileAppender.setRollingPolicy(filePolicy);
                fileAppender.start();

                log.addAppender(fileAppender);
            }

            log.setLevel(applicationLoggingConfiguration.getLoggerLevel());
        } else
            log.setLevel(Level.OFF);

        log.setAdditive(false);
        Runtime.getRuntime().addShutdownHook(Thread.ofVirtual().start(this::safelyClose));
    }

    private void safelyClose() {
        try {
            close();
        } catch (IOException io) {
            throw new FactoryCloseableException(io);
        }
    }

    @Override
    public ILoggingConfiguration getConfiguration() {
        return applicationLoggingConfiguration;
    }
}
