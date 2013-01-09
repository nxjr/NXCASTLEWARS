package CastleWars;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Keyboard;
import org.powerbot.game.api.methods.interactive.Players;

    public class joinEarly extends Node {
                @Override
                public boolean activate() {
                	
            return 	Widgets.get(1188).validate() && Widgets.get(1188, 3).validate()
            		&& !NxCastleWars.SARA_BASE.contains(Players.getLocal())
            		&& !NxCastleWars.ZAMMY_BASE.contains(Players.getLocal())
            		&& !NxCastleWars.LOBBYY.contains(Players.getLocal());
                }

                @Override
                public void execute() {
                	System.out.println("Joining Early...");
                	NxCastleWars.Status = "Joining Early";
                	if(Widgets.get(1188).validate()){
                	Keyboard.sendKey('1');
                	Task.sleep(100);
            		
                }else if (Widgets.get(1186).validate()){
                	Widgets.get(1186).getChild(7).click(true);
                	Task.sleep(200, 500);
                }
        

}
    }
