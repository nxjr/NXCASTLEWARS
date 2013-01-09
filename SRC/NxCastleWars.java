package CastleWars;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.powerbot.core.event.events.MessageEvent;
import org.powerbot.core.event.listeners.MessageListener;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;

import org.powerbot.core.script.job.Job;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.input.Keyboard;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.widget.WidgetCache;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.bot.Context;
import org.powerbot.game.client.Client;


@Manifest(authors = { "NxJr" }, name = "NxCastleWars", description = "Pick your world! Does the castlewars mini-game for tickets and to gain games. Added afk option", website = "http://www.powerbot.org/community/topic/873598-nxcastlewars-gui-get-your-halo-5000-games-supports-hybrid-helmets/", version = 1.32)
public class NxCastleWars extends ActiveScript implements PaintListener,  MouseListener{
//else


	//Paint
	public static int gamesWon = 0;
	public static int gamesTied = 0;
	public static int gamesLoss = 0;
	public static int gamesPlayed = 0;
	public static long startTime;
	Timer runTime = new Timer(0);
	public static int ticketsGained = 0;
	public static String Status = "0";
	
	
	//Objects
		static final int[] BARRIER = {4470, 4469};
		static final int[] LADDER = {6280, 6281};

	    static final int GUTIX= 4408;
	    static final int ticket= 4067;
	    static final int CHEST_ID = 4483;
	    static final int SARA = 4387;
	    static final int ZAMMY = 4388;
	    static final Area LOBBYY = new Area(new Tile(2457, 3077, 0),new Tile(2434, 3103, 0));
	    static final Area SARA_BASE = new Area(new Tile(2423, 3072, 1),new Tile(3432, 3081, 1));
	    static final Area ZAMMY_BASE= new Area(new Tile(2368, 3127, 1),new Tile(2377, 3136, 1));
	    static final int BARREL = 36353;
	    
	    //GUI
	    private boolean guiWait = true;
	    gui g = new gui();
		
	   //Paint
	    private final Color color6 = new Color(102, 255, 51);
	    private final Color color7 = new Color(0, 0, 0);
	    private final Color color8 = new Color(255, 0, 51);
        private final Font font3 = new Font("Arial Black", 1, 17);
	    private final Color color1 = new Color(0, 153, 0);
        private final Color color2 = new Color(204, 0, 0);
        private final Color color3 = new Color(255, 153, 0);
        private final Color color4 = new Color(0, 153, 204);
        private final Color color5 = new Color(255, 255, 255);
        private final BasicStroke stroke1 = new BasicStroke(1);
        private final Font font1 = new Font("Arial", 0, 8);
        private final Font font2 = new Font("Arial", 0, 9);
        private Client client = Context.client();
        private final Image img1 = getImage("http://img42.imageshack.us/img42/6003/nxcastlewars.png");

        private boolean ShowPaint = true;
        
        private Rectangle Hider = new Rectangle(490, 392, 22, 21);
	    
	    private final List<Node> jobsCollection = Collections.synchronizedList(new ArrayList<Node>());
        private Tree jobContainer = null;

        public synchronized final void provide(final Node... jobs) {
                for (final Node job : jobs) {
                        if(!jobsCollection.contains(job)) {
                                jobsCollection.add(job);
                        }
                }
                jobContainer = new Tree(jobsCollection.toArray(new Node[jobsCollection.size()]));
        }

        public synchronized final void revoke(final Node... jobs) {
                for (final Node job : jobs) {
                        if(jobsCollection.contains(job)) {
                                jobsCollection.remove(job);
                        }
                }
                jobContainer = new Tree(jobsCollection.toArray(new Node[jobsCollection.size()]));
        }

        public final void submit(final Job... jobs) {
                for (final Job job : jobs) {
                        getContainer().submit(job);
                }
        }

        @Override
        public void onStart() {
        	
        	Status = "Starting Up";
        	startTime = System.currentTimeMillis();
    		g.setVisible(true);
    		while(guiWait == true) sleep(500);
    		
    		
        }
        private Image getImage(String url) {
            try {
                return ImageIO.read(new URL(url));
            } catch(IOException e) {
                return null;
            }
        }

      

        @Override
        public int loop() {
        
        	if (Game.getClientState() != Game.INDEX_MAP_LOADED) {
        		return 1000;
        		}

        		if (client != Context.client()) {
        		WidgetCache.purge();
        		Context.get().getEventManager().addListener(this);
        		client = Context.client();
        		}
        		if (jobContainer != null) {
        		final Node job = jobContainer.state();
        		if (job != null) {
        		jobContainer.set(job);
        		getContainer().submit(job);
        		job.join();
        		}
        		}
        		return Random.nextInt(10, 50);
                
        }
        

public class gui extends JFrame {
	public gui() {
		initComponents();
	}

	private void button1ActionPerformed(ActionEvent e) {
		String picked = comboBox1.getSelectedItem().toString();
		String chosen = enterHow.getSelectedItem().toString();
		String afk = comboBox2.getSelectedItem().toString();
		String world = comboBox3.getSelectedItem().toString();
		if (chosen.equals("Join the Sara Portal")){
			provide(new joinSara());
			provide(new AntiBan());
			provide(new Closeinterface());
        	provide(new joinEarly());
        	
		}else if  (chosen.equals("Join the Zammy Portal")){
			provide(new joinZammy());
			provide(new AntiBan());
			provide(new Closeinterface());
        	provide(new joinEarly());
        	
	}else{
		provide(new joinGuthix());
		provide(new AntiBan());
		provide(new Closeinterface());
    	provide(new joinEarly());
  	}if (picked.equals("Trickster Hood")){
		provide(new HybridArmourTrick());

	}else if (picked.equals("Battle Hood")){
		provide(new HybridArmourBattle());
}else {
		provide(new HybridArmourVang());
	}
	if (afk.equals("Climb ladder")){
		provide(new climbLadder());
	}else{
		provide(new passBarrier());
	}
	if (world.equals("24")){
		Context.setLoginWorld(24);
	}else if (world.equals("52")){
		Context.setLoginWorld(52);
	}else{
		Context.setLoginWorld(59);
	}
	guiWait = false;
	this.dispose();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - bai pollard
		label1 = new JLabel();
		button1 = new JButton();
		label2 = new JLabel();
		enterHow = new JComboBox<>();
		label3 = new JLabel();
		comboBox1 = new JComboBox<>();
		label4 = new JLabel();
		comboBox2 = new JComboBox<>();
		label5 = new JLabel();
		comboBox3 = new JComboBox<>();

		//======== this ========
		setTitle("NxWars");
		Container contentPane = getContentPane();

		//---- label1 ----
		label1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		label1.setText("NxCastleWars by NxJr");

		//---- button1 ----
		button1.setText("Start Script");
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button1ActionPerformed(e);
			}
		});

		//---- label2 ----
		label2.setText("How to join :");

		//---- enterHow ----
		enterHow.setModel(new DefaultComboBoxModel<>(new String[] {
			"Join the Sara Portal",
			"Join the Zammy Portal",
			"Join the Guthix Portal"
		}));

		//---- label3 ----
		label3.setText("Hybrid hood :");

		//---- comboBox1 ----
		comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
			"Trickster Hood",
			"Battle Hood",
			"Vangaurd Hood"
		}));

		//---- label4 ----
		label4.setText("How to afk : ");

		//---- comboBox2 ----
		comboBox2.setModel(new DefaultComboBoxModel<>(new String[] {
			"Climb ladder",
			"Pass barrier"
		}));

		//---- label5 ----
		label5.setText("What world :");

		//---- comboBox3 ----
		comboBox3.setModel(new DefaultComboBoxModel<>(new String[] {
			"24",
			"52",
			"59"
		}));

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(contentPaneLayout.createParallelGroup()
						.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
							.addComponent(label2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(12, 12, 12)
							.addComponent(enterHow, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(189, 189, 189))
						.addComponent(label1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addGroup(contentPaneLayout.createParallelGroup()
								.addGroup(contentPaneLayout.createSequentialGroup()
									.addComponent(label3)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
								.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
									.addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
										.addComponent(label5)
										.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(comboBox3))
									.addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
										.addComponent(label4)
										.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(comboBox2, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)))
								.addComponent(button1, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE))
							.addGap(0, 0, Short.MAX_VALUE))))
		);
		contentPaneLayout.setVerticalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addComponent(label1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(enterHow, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label2, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(label3)
						.addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(label4)
						.addComponent(comboBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(label5)
						.addComponent(comboBox3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(button1, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
		);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - bai pollard
	private JLabel label1;
	private JButton button1;
	private JLabel label2;
	private JComboBox<String> enterHow;
	private JLabel label3;
	private JComboBox<String> comboBox1;
	private JLabel label4;
	private JComboBox<String> comboBox2;
	private JLabel label5;
	private JComboBox<String> comboBox3;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
        	

 

        
        
        
        
        //Booleans
          
        public static boolean PlayerInLobby() {
        	
        	 return LOBBYY.contains(Players.getLocal().getLocation());
        	 }

        public static boolean PlayerAtzammy() {
        	        	 return ZAMMY_BASE.contains(Players.getLocal().getLocation());
        	 }
        public static boolean PlayerAtsara() {
       	
       	 return SARA_BASE.contains(Players.getLocal().getLocation());
       	 }



      @Override
        public void onRepaint(Graphics g1) {
    	  long RunTime = System.currentTimeMillis() - startTime;
      	int gAmesPerHour = (int) ((3600000.0 / (double) RunTime) * gamesPlayed);
	      int ticketsPerHour = (int) ((3600000.0 / (double) RunTime) * ticketsGained);
	      Graphics2D g = (Graphics2D)g1;
	      if(ShowPaint){ 
        	  
        	      g.drawImage(img1, 3, 390, null);
        	        g.setFont(font1);
        	        g.setColor(color1);
        	        g.drawString(""+ gamesWon, 234, 484);
        	        g.setColor(color2);
        	        g.drawString("" + gamesLoss, 255, 483);
        	        g.setColor(color3);
        	        g.drawString("" + gamesTied, 280, 483);
        	        g.setColor(color4);
        	        g.drawString(""+ gAmesPerHour, 301, 483);
        	        g.setFont(font2);
        	        g.setColor(color5);
        	        g.drawString("" + Status, 357, 443);
        	        g.drawString(""+  ticketsPerHour, 373, 467);
        	        g.drawString("" + runTime.toElapsedString(), 165, 443);
        	        g.drawString(""+ ticketsGained,  191, 468);
        	        g.setColor(color3);
        	        g.drawString(""+ gamesPlayed, 258, 454);
        	        g.setColor(color6);
        	        g.fillRect(490, 392, 22, 21);
        	        g.setColor(color7);
        	        g.setStroke(stroke1);
        	        g.drawRect(490, 392, 22, 21);
        	        g.setFont(font3);
        	        g.drawString("X", 494, 408);
        	    
        	   }else{
        		g.setColor(color8);
       	        g.fillRect(490, 392, 22, 21);
       	        g.setColor(color7);
       	        g.setStroke(stroke1);
       	        g.drawRect(490, 392, 22, 21);
       	        g.setFont(font3);
       	        g.drawString("O", 494, 408);
       	      
               }
        }

	@Override
	public void mouseClicked(MouseEvent e) {
		if(Hider.contains(e.getPoint())){
			ShowPaint = !ShowPaint;
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

       
    



	
       