package CastleWars;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class climbLadder extends Node {

	@Override
	public boolean activate() {
		SceneObject barrier = SceneEntities.getNearest(NxCastleWars.LADDER);
		return  barrier != null
            	&&	NxCastleWars.SARA_BASE.contains(Players.getLocal()) || 
        		NxCastleWars.ZAMMY_BASE.contains(Players.getLocal());
	}

	@Override
	public void execute() {
		NxCastleWars.Status = "Climbing ladder...";
   	 SceneObject ladder = SceneEntities.getNearest(NxCastleWars.LADDER);
   	if (ladder != null) {
            if (!ladder.isOnScreen()) {
                Walking.walk(ladder);
                Camera.turnTo(ladder);
            } else {
                ladder.interact("Climb");
                Task.sleep(4000, 5000);
            }
        }
    }

	}


