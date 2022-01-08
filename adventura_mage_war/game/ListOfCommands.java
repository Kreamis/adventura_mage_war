package game;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ListOfCommands {
    private Map<String, ICommand> mapOfCommands = new HashMap<>();
    public ListOfCommands() {
    }

        public void enterCommand(ICommand command) {
        this.mapOfCommands.put(command.getName(), command);
    }

        public ICommand returnCommand(String chain) {
        return this.mapOfCommands.containsKey(chain) ? (ICommand)this.mapOfCommands.get(chain) : null;
    }

        public boolean isCommandValid(String chain) {
        return this.mapOfCommands.containsKey(chain);
    }

        public String returnNameOfCommand() {
        String list = ", ";

        String wordOfCommand;
        for(Iterator var2 = this.mapOfCommands.keySet().iterator(); var2.hasNext(); list = list + wordOfCommand + " ") {
            wordOfCommand = (String)var2.next();
        }

        return list;
    }


}
