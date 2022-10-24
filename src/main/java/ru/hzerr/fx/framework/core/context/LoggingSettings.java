package ru.hzerr.fx.framework.core.context;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.PatternLayout;
import ru.hzerr.fx.framework.logging.encoder.ColoredPatternLayoutEncoder;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LoggingSettings implements Settings {

    private static final String FORMATTED_TIME = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(Calendar.getInstance().getTime());
    private PatternLayout consolePatternLayoutOfFXFramework = new ColoredPatternLayoutEncoder();
    private String frameworkLogFileName = "fx-" + FORMATTED_TIME + ".log";
    private String frameworkLoggerConsolePattern = "[%thread] %highlight(%-5level) | %time(" + FORMATTED_TIME + ") | - %classname(%class{0}:) %highlight(%msg) %n";
    private String frameworkLoggerFilePattern = "[%thread] %-5level | " + FORMATTED_TIME + " | - %class{0}: %msg %n";
    private String frameworkLoggerName = "FXFramework";
    private Charset frameworkFileEncoding = StandardCharsets.UTF_8;
    private Charset frameworkConsoleEncoding = StandardCharsets.UTF_8;
    private Level frameworkLoggerLevel = Level.DEBUG;
    private boolean useLoggerOfFXFramework = true;
    private boolean useConsoleLoggingOfFXFramework = true;
    private boolean useFileLoggingOfFXFramework = true;

    public String getFrameworkLogFileName() {
        return frameworkLogFileName;
    }

    public void setFrameworkLogFileName(String frameworkLogFileName) {
        this.frameworkLogFileName = frameworkLogFileName;
    }

    public String getFrameworkLoggerConsolePattern() {
        return frameworkLoggerConsolePattern;
    }

    public void setFrameworkLoggerConsolePattern(String frameworkLoggerConsolePattern) {
        this.frameworkLoggerConsolePattern = frameworkLoggerConsolePattern;
    }

    public String getFrameworkLoggerFilePattern() {
        return frameworkLoggerFilePattern;
    }

    public void setFrameworkLoggerFilePattern(String frameworkLoggerFilePattern) {
        this.frameworkLoggerFilePattern = frameworkLoggerFilePattern;
    }

    public String getFrameworkLoggerName() {
        return frameworkLoggerName;
    }

    public void setFrameworkLoggerName(String frameworkLoggerName) {
        this.frameworkLoggerName = frameworkLoggerName;
    }

    public Charset getFrameworkFileEncoding() {
        return frameworkFileEncoding;
    }

    public void setFrameworkFileEncoding(Charset frameworkFileEncoding) {
        this.frameworkFileEncoding = frameworkFileEncoding;
    }

    public Charset getFrameworkConsoleEncoding() {
        return frameworkConsoleEncoding;
    }

    public void setFrameworkConsoleEncoding(Charset frameworkConsoleEncoding) {
        this.frameworkConsoleEncoding = frameworkConsoleEncoding;
    }

    public Level getFrameworkLoggerLevel() {
        return frameworkLoggerLevel;
    }

    public void setFrameworkLoggerLevel(Level frameworkLoggerLevel) {
        this.frameworkLoggerLevel = frameworkLoggerLevel;
    }

    public boolean shouldUseLoggerOfFXFramework() {
        return useLoggerOfFXFramework;
    }

    public void setUseLoggerOfFXFramework(boolean useLoggerOfFXFramework) {
        this.useLoggerOfFXFramework = useLoggerOfFXFramework;
    }

    public boolean shouldUseConsoleLoggingOfFXFramework() {
        return useConsoleLoggingOfFXFramework;
    }

    public void setUseConsoleLoggingOfFXFramework(boolean useConsoleLoggingOfFXFramework) {
        this.useConsoleLoggingOfFXFramework = useConsoleLoggingOfFXFramework;
    }

    public boolean shouldUseFileLoggingOfFXFramework() {
        return useFileLoggingOfFXFramework;
    }

    public void setUseFileLoggingOfFXFramework(boolean useFileLoggingOfFXFramework) {
        this.useFileLoggingOfFXFramework = useFileLoggingOfFXFramework;
    }

    public PatternLayout getConsolePatternLayoutOfFXFramework() {
        return consolePatternLayoutOfFXFramework;
    }

    public void setConsolePatternLayoutOfFXFramework(PatternLayout consolePatternLayoutOfFXFramework) {
        this.consolePatternLayoutOfFXFramework = consolePatternLayoutOfFXFramework;
    }
}
