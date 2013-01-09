package CastleWars;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.node.SceneObject;


public class joinSara extends Node {

	@Override
	public boolean activate() {
		SceneObject s = SceneEntities.getNearest(NxCastleWars.SARA);
    		return  s !=null
    				&& NxCastleWars.PlayerInLobby() 
    				&& !Widgets.get(985).validate()
    				&& !Widgets.get(1127).validate();
            			 
	}

	@Override
	public void execute() {
		NxCastleWars.Status = "Joining Sara...";
		System.out.println("Joining Sara");
		SceneObject s = SceneEntities.getNearest(NxCastleWars.SARA);
        if (s != null){
        	if (!s.isOnScreen()){
      			 Walking.walk(s);
      			 Camera.turnTo(s);
      		  }else {
      			 s.interact("Enter");
      			 Task.sleep(1500, 1750);
      		  }
      	  }
        }
          	 

          }
  