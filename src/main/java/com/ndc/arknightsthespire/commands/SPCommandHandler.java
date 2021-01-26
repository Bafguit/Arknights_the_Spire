package com.ndc.arknightsthespire.commands;

import basemod.DevConsole;
import basemod.devcommands.ConsoleCommand;
import com.ndc.arknightsthespire.SPHandler;

import java.util.ArrayList;

public class SPCommandHandler extends ConsoleCommand {

    public SPCommandHandler() {
        this.maxExtraTokens = 2; //How many additional words can come after this one. If unspecified, maxExtraTokens = 1.
        this.minExtraTokens = 0; //How many additional words have to come after this one. If unspecified, minExtraTokens = 0.
        this.requiresPlayer = true; //if true, means the command can only be executed if during a run. If unspecified, requiresplayer = false.
    }

    @Override
    protected void execute(String[] tokens, int depth) {
        System.out.println(tokens.length);
        if(tokens.length == 1 || tokens.length > 3)
            DevConsole.log("Usage: sp add/remove/set/addMax/removeMax/setMax <amount>");
        else {
            switch (tokens[1]) {
                case "add":
                    SPHandler.addSp(handleAmountValue(tokens), true);
                    break;
                case "remove":
                    SPHandler.removeSp(handleAmountValue(tokens), true);
                    break;
                case "set":
                    SPHandler.setSp(handleAmountValue(tokens), true);
                    break;
                case "addMax":
                    SPHandler.addMaxSp(handleAmountValue(tokens), true);
                    break;
                case "removeMax":
                    SPHandler.removeMaxSp(handleAmountValue(tokens), true);
                    break;
                case "setMax":
                    SPHandler.setMaxSp(handleAmountValue(tokens), true);
                    break;
                default:
                    DevConsole.log("Usage: sp add/remove/set/addMax/removeMax/setMax <amount>");
            }
        }
    }

    public ArrayList<String> extraOptions(String[] tokens, int depth) {
        ArrayList<String> result = new ArrayList<>();
        if(tokens.length == 2) {
            result.add("add");
            result.add("remove");
            result.add("set");
            result.add("addMax");
            result.add("removeMax");
            result.add("setMax");
        } else {
            if(tokens.length == 3)
                result.add("<amount>");
            //complete = true;
        }

        return result;
    }

    //I don't think this is a good coding practice, either.
    //If you have any suggestions to make it better, feel free to advise me.
    private int handleAmountValue(String[] tokens) {
        int amount = -1;
        if(tokens.length == 2) {
            DevConsole.log("Usage: sp " + tokens[1] + " <amount>");
        } else {
            try {
                amount = Integer.parseInt(tokens[2]);
            } catch (NumberFormatException e) {
                amount = -1;
            }
            if(amount < 0) {
                DevConsole.log("The amount must be a positive integer.");
            }
        }
        return amount;
    }
}
