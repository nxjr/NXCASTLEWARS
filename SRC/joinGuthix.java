package CastleWars;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.node.SceneObject;


public class joinGuthix extends Node {

	@Override
	public boolean activate() {
		SceneObject g = SceneEntities.getNearest(NxCastleWars.GUTIX);
    	return 	NxCastleWars.PlayerInLobby()
    			&& g !=null
    			&& !Widgets.get(985).validate()
    			&& !Widgets.get(1127).validate();
   
            			
	}

	@Override
	public void execute() {
		NxCastleWars.Status = "Joining Guthix...";
		System.out.println("Joining Guthix");
		SceneObject g = SceneEntities.getNearest(NxCastleWars.GUTIX);
        if (g != null){
      	  if (NxCastleWars.PlayerInLobby()){
      		  if (!g.isOnScreen()){
      			 Walking.walk(g);
      			 Camera.turnTo(g);
      			 Task.sleep(200, 400);
      		  }else {
      			 g.interact("Enter");
      			Task.sleep(2000, 2500);
      		  }
      	  }
        }
          	 

          }
  }