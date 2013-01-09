package CastleWars;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.methods.widget.Lobby;
import org.powerbot.game.api.util.Random;

    public class AntiBan extends Node {
                @Override
                public boolean activate() {
             return !Widgets.get(1188).validate() 
            		 && !NxCastleWars.SARA_BASE.contains(Players.getLocal())
             		 && !NxCastleWars.ZAMMY_BASE.contains(Players.getLocal())
            		 && !Widgets.get(985).validate()
            		 && !NxCastleWars.LOBBYY.contains(Players.getLocal())
            		 && !Widgets.get(1127).validate()
            		 && !Widgets.get(1186).validate()
            		 && !Lobby.isOpen();
            		
                }
                

             
                		

				@Override
				public void execute() {
					System.out.println("Antiban");
					NxCastleWars.Status = "Doing AntiBan...";
					 //Max and Min Time
                    int minMilliSecond = 500;
                    int maxMillisecond = 50000;
                    Task.sleep(Random.nextInt(minMilliSecond, maxMillisecond));
         
                    int i=Random.nextInt(1,50);
                    switch (i){
                        case 1: Tabs.ATTACK.open();
                            break;
                        case 2: Tabs.CLAN_CHAT.open();
                            break;
                        case 4: Tabs.EMOTES.open();
                            break;
                        case 5: Tabs.EQUIPMENT.open();
                            break;
                        case 6: Tabs.FRIENDS.open();
                            break;
                        case 7: Tabs.FRIENDS_CHAT.open();
                            break;
                        case 8: Tabs.INVENTORY.open();
                            break;
                       
                            
                        case 10: Tabs.NONE.open();
                            break;
                        case 11: Tabs.OPTIONS.open();
                            break;
                        case 12: Tabs.PRAYER.open();
                            break;
                        case 13: Tabs.STATS.open();
                            break;
                    
                            
                        case 17:
                          
                            Camera.setNorth();
                            break;
         
                        default:
                           
                            //randomly generated numbers for mouse
                            int x=Random.nextInt(1,450);
                            int y=Random.nextInt(1,450);
                            int randomX= Random.nextInt(1,300);
                            int randomY=Random.nextInt(1,300);
                            Mouse.move(x,y,randomX,randomY);
                           
                            int ii=Random.nextInt(1,20);
                            switch (ii){
                                case 1:
                                   
                                    Camera.setAngle(Random.nextInt(1, 450));
                                    break;
                                case 2:
                                   
                                    Camera.setPitch(Random.nextInt(1, 450));
                                    break;
         
                                case 3:
                                    
                                    Camera.setAngle(Random.nextInt(10, 500));
                                    Camera.setPitch(Random.nextInt(10, 500));
                                    break;
                                case 4:
                                    
                                    Camera.setAngle(Random.nextInt(20, 300));
                                    break;
                                case 5:
                                    
                                        Mouse.move(Random.nextInt(Mouse.getLocation().x - 150,
                                                        Mouse.getLocation().x + 150),
                                            Random.nextInt(Mouse.getLocation().y - 150,
                                                        Mouse.getLocation().y + 150));
                                        break;
         
                                default:
                                    break;
                            }
                            break;
         
                    }
                }
         


           	 
				
					
					
				}
    

