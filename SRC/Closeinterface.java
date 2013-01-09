package CastleWars;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;


public class Closeinterface extends Node {

	@Override
	public boolean activate() {
		return Widgets.get(985).validate() && Widgets.get(985, 77).validate();
	}

	@Override
	public void execute() {
		NxCastleWars.Status = "Closing interface...";
		
		if (Widgets.get(985).validate()){
			if (Widgets.get(985).getChild(19).getText().contains("1 ticket")) {
	            NxCastleWars.ticketsGained ++;
	            NxCastleWars.gamesTied ++;
	           
	         
			 } else if (Widgets.get(985).getChild(19).getText().contains("2 tickets")) {
	          NxCastleWars.ticketsGained +=2;
	          NxCastleWars.gamesWon ++ ;
			 }else if (Widgets.get(985).getChild(19).getText().contains("Nothing")) {
	
		NxCastleWars.gamesLoss++;
		
        	
			 }
	
} 
	Widgets.get(985).getChild(77).click(true);
             Task.sleep(1000);
             NxCastleWars.gamesPlayed ++;
             


}
}
