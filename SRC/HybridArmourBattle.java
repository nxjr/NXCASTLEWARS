package CastleWars;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;

public class HybridArmourBattle extends Node {

	@Override
	public boolean activate() {
		
		return Widgets.get(1127).getChild(58).validate();
	}

	@Override
	public void execute() {
		NxCastleWars.Status = "Getting Hybrid Hood";
		 if (Widgets.get(1127).validate()){
			 Widgets.get(1127).getChild(22).getChild(1).click(true);
			 Task.sleep(1000);
		 }
}
}