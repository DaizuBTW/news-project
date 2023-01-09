package by.it.selvanovich.news.controller;

import java.util.HashMap;
import java.util.Map;

import by.it.selvanovich.news.controller.impl.*;

public class CommandProvider {
    private Map<CommandName, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(CommandName.GO_TO_BASE_PAGE, new GoToBasePage());
        commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPageCommand());
        commands.put(CommandName.DO_REGISTRATION, new DoRegistration());
        commands.put(CommandName.DO_SIGN_IN, new DoSIgnIn());
        commands.put(CommandName.DO_SIGN_OUT, new DoSignOut());
        commands.put(CommandName.DO_ADD_NEWS, new DoAddNews());
        commands.put(CommandName.DO_UPDATE_NEWS, new DoUpdateNews());
        commands.put(CommandName.DO_DELETE_NEWS, new DoDeleteNews());
        commands.put(CommandName.GO_TO_NEWS_LIST, new GoToNewsList());
        commands.put(CommandName.GO_TO_VIEW_NEWS, (Command) new GoToViewNews());
        commands.put(CommandName.GO_TO_ADD_NEWS, new GoToAddNews());
        commands.put(CommandName.GO_TO_UPDATE_NEWS, new GoToUpdateNews());
    }


    public Command getCommand(String name) {
        CommandName commandName = CommandName.valueOf(name.toUpperCase());
        Command command = commands.get(commandName);
        return command;
    }

}