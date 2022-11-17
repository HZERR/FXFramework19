package ru.hzerr.fx.framework.logging.factory;

import ch.qos.logback.classic.Level;
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
import ru.hzerr.file.exception.file.HFileNotFoundException;
import ru.hzerr.fx.framework.core.FxApplicationContext;
import ru.hzerr.fx.framework.logging.policy.CancelRollingPolicy;

import java.io.IOException;

public class FXFrameworkSessionLogFactory extends LogFactory {

    private BaseFile sessionLogFile;
    private final LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
    private final PatternLayout consolePatternLayout = FxApplicationContext.getLoggingSettings().getConsolePatternLayoutOfFXFramework();
    private final LayoutWrappingEncoder<ILoggingEvent> consoleEncoder = new LayoutWrappingEncoder<>();
    private final ConsoleAppender<ILoggingEvent> consoleAppender = new ConsoleAppender<>();
    private final CancelRollingPolicy filePolicy = new CancelRollingPolicy();
    private final PatternLayoutEncoder fileEncoder = new PatternLayoutEncoder();
    private final RollingFileAppender<ILoggingEvent> fileAppender = new RollingFileAppender<>();

    public FXFrameworkSessionLogFactory() {
    }

    public void createSession() throws IOException {
        log = lc.getLogger(FxApplicationContext.getLoggingSettings().getFrameworkLoggerName());

        if (FxApplicationContext.getLoggingSettings().shouldUseLoggerOfFXFramework()) {
            if (FxApplicationContext.getLoggingSettings().shouldUseConsoleLoggingOfFXFramework()) {
                consolePatternLayout.setContext(lc);
                consolePatternLayout.setPattern(FxApplicationContext.getLoggingSettings().getFrameworkLoggerConsolePattern());
                consolePatternLayout.start();

                consoleEncoder.setLayout(consolePatternLayout);
                consoleEncoder.setCharset(FxApplicationContext.getLoggingSettings().getFrameworkConsoleEncoding());

                consoleAppender.setContext(lc);
                consoleAppender.setEncoder(consoleEncoder);
                consoleAppender.start();

                log.addAppender(consoleAppender);
            }

            if (FxApplicationContext.getLoggingSettings().shouldUseFileLoggingOfFXFramework()) {
                sessionLogFile = FxApplicationContext.getStructureConfiguration().getLogDirectory().createSubFile(FxApplicationContext.getLoggingSettings().getFrameworkLogFileName());
                if (sessionLogFile.notExists())
                    throw new HFileNotFoundException("Unable to create a log file of the current session");

                filePolicy.setContext(lc);
                filePolicy.setParent(fileAppender);
                filePolicy.setFileNamePattern("%d{yyyy-MM-dd HH-mm-ss}.log");
                filePolicy.start();

                fileEncoder.setContext(lc);
                fileEncoder.setCharset(FxApplicationContext.getLoggingSettings().getFrameworkFileEncoding());
                fileEncoder.setPattern(FxApplicationContext.getLoggingSettings().getFrameworkLoggerFilePattern());
                fileEncoder.start();

                fileAppender.setContext(lc);
                fileAppender.setEncoder(fileEncoder);
                fileAppender.setAppend(true);
                fileAppender.setFile(sessionLogFile.getLocation());
                fileAppender.setRollingPolicy(filePolicy);
                fileAppender.start();

                log.addAppender(fileAppender);
            }

            log.setLevel(FxApplicationContext.getLoggingSettings().getFrameworkLoggerLevel());
        } else
            log.setLevel(Level.OFF);

        log.setAdditive(false);
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
}
