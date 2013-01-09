package CastleWars;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.node.SceneObject;



  public class passBarrier extends Node {
	  SceneObject barrier = SceneEntities.getNearest(NxCastleWars.BARRIER);
                @Override
                public boolean activate() {
                	 				
             
                        return  barrier != null
                        	&&	NxCastleWars.SARA_BASE.contains(Players.getLocal()) || 
                        		NxCastleWars.ZAMMY_BASE.contains(Players.getLocal());
                        		
                }

                @Override
                public void execute() {
                	NxCastleWars.Status = "Passing barrier...";
                	 SceneObject ladder = SceneEntities.getNearest(NxCastleWars.BARRIER);
                	if (ladder != null) {
                         if (!ladder.isOnScreen()) {
                             Walking.walk(ladder);
                             Camera.turnTo(ladder);
                         } else {
                             ladder.interact("Pass");
                             Task.sleep(4000, 5000);
                         }
                     }
                 }
             
                }
        