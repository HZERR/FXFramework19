package ru.hzerr.fx.framework.config.args;

import ru.hzerr.fx.framework.config.ConfigurationBuilder;
import ru.hzerr.fx.framework.core.context.FxApplicationContext;

public class ArgsConfigurationBuilder implements ConfigurationBuilder<ArgsConfiguration> {

    private static final ArgsConfiguration argsConfiguration = new ArgsConfiguration();

    @Override
    public ArgsConfiguration getConfiguration() {
//        final OptionsParser parser = OptionsParser.newOptionsParser(InternalFrameworkOptions.class);
//        parser.parseAndExitUponError(FxApplicationContext.getInitializationSettings().getArgs());
//        argsConfiguration.setOptions(parser.getOptions(InternalFrameworkOptions.class));
        return argsConfiguration;
    }
}
