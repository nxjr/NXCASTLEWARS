package CastleWars;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.node.SceneObject;

 public class joinZammy extends Node {
                @Override
                public boolean activate() {
                	 SceneObject z = SceneEntities.getNearest(NxCastleWars.ZAMMY);
                	return  z !=null
                			&& NxCastleWars.PlayerInLobby() 
                        	&& !Widgets.get(985).validate()
                        	&& !Widgets.get(1127).validate();
                      	
                }

                @Override
                public void execute() {
                NxCastleWars.Status = "Joining Zammy...";
            System.out.println("Joining Zammy");
              SceneObject z = SceneEntities.getNearest(NxCastleWars.ZAMMY);
              if (z != null){
            	  if (NxCastleWars.PlayerInLobby()){
            		  if (!z.isOnScreen()){
            			  Walking.walk(z);
            			  Camera.turnTo(z);
            			   Task.sleep(1000,1500);
            		  }else{
            			 z.interact("Enter");
            			  Task.sleep(1500, 2000);
            		  }
            	  }
              }
                	 

                }
        }

